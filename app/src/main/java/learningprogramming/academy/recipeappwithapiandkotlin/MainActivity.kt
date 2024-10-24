package learningprogramming.academy.recipeappwithapiandkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import learningprogramming.academy.recipeappwithapiandkotlin.ui.theme.RecipeAppWithApiAndKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            RecipeAppWithApiAndKotlinTheme {
                Scaffold() { innerPadding ->
                    RecipeApp(Modifier.fillMaxSize().padding(innerPadding), navController = navController)
                }
            }
        }
    }
}



