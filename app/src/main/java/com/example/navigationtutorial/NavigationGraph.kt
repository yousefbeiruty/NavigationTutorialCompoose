package com.example.navigationtutorial


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.navigationtutorial.screens.AddPostScreen
import com.example.navigationtutorial.screens.HomeDetailsScreen
import com.example.navigationtutorial.screens.HomeScreen
import com.example.navigationtutorial.screens.JobScreen
import com.example.navigationtutorial.screens.NetworkScreen
import com.example.navigationtutorial.screens.NotificationScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    val sharedViewModel:SharedViewModel= hiltViewModel()
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            sharedViewModel.visibleBottom(true)
            HomeScreen(navController)
        }
        composable(BottomNavItem.MyNetwork.screen_route) {
            sharedViewModel.visibleBottom(true)
            NetworkScreen()
        }
        composable(BottomNavItem.AddPost.screen_route) {
            sharedViewModel.visibleBottom(true)
            AddPostScreen()
        }
        composable(BottomNavItem.Notification.screen_route) {
            sharedViewModel.visibleBottom(true)
            NotificationScreen()
        }
        composable(BottomNavItem.Jobs.screen_route) {
            sharedViewModel.visibleBottom(true)
            JobScreen()
        }

        composable(Screen.DetailsScreee.route){
           sharedViewModel.visibleBottom(false)
           HomeDetailsScreen()
        }
    }
}