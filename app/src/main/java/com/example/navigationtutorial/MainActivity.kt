package com.example.navigationtutorial

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.navigationtutorial.ui.theme.LocalColors
import com.example.navigationtutorial.ui.theme.NavigationTutorialTheme
import com.example.navigationtutorial.ui.theme.myColors
import com.example.navigationtutorial.ui.theme.myTypography
import com.example.navigationtutorial.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.wait

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White//MaterialTheme.colorScheme.
                ) {
                    MainScreenView()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreenView(sharedViewModel: SharedViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    Scaffold(topBar = { TopAppBarSample(navController, sharedViewModel.flag) }, bottomBar = {
        if (sharedViewModel.flag) BottomNavigation(navController = navController)
    }) {
        NavigationGraph(navController = navController)
    }
}

@Composable
fun TopAppBarSample(navController: NavHostController, flag: Boolean) {
    Column {
        TopAppBar(elevation = 4.dp, title = {
            Text(
                "I'm a TopAppBar", style =
                //TextStyle(
//                    fontSize = 17.sp,
//                    fontStyle = FontStyle.Normal,
//                    fontWeight = FontWeight.Bold,
//                    color = if (isSystemInDarkTheme()) MaterialTheme.myColors.white
//                    else Color.Black
//
                    MaterialTheme.myTypography.bigText
              //  )
            )
        }, backgroundColor = MaterialTheme.colorScheme.inversePrimary, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                if (!flag) Icon(Icons.Filled.ArrowBack, null,
                    tint=Color.White)
            }
        }, actions = {
            IconButton(onClick = {/* Do Something*/ }) {
                Icon(Icons.Filled.Share, null)
            }
            IconButton(onClick = {/* Do Something*/ }) {
                Icon(Icons.Filled.Settings, null)
            }
        })
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    NavigationTutorialTheme {
        MainScreenView()
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    NavigationTutorialTheme {
//        Greeting("Android")
//    }
//}