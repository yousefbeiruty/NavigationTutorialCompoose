package com.example.navigationtutorial.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.navigationtutorial.repository.ApiRepository
import retrofit2.HttpException

class GenericPagingSource<T : Any>(
    private val repository: ApiRepository,
    private val error:(String)->Unit,
    private val pageFetcher: suspend (Int) -> List<T>
) : PagingSource<Int, T>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return try {
            val currentPage = params.key ?: 1
            val data = pageFetcher(currentPage)
            LoadResult.Page(
                data = data,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (data.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            error(e.message.toString())
            LoadResult.Error(e)
        } catch (exception: HttpException) {
            error(exception.message.toString())
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return null
    }
}
