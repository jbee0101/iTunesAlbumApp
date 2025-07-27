package com.example.itunesalbumapp.presentation.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.itunesalbumapp.presentation.ui.screen.AlbumDetailScreen
import com.example.itunesalbumapp.presentation.ui.screen.AlbumListScreen
import com.example.itunesalbumapp.presentation.viewmodel.AlbumDetailViewModel
import com.example.itunesalbumapp.presentation.viewmodel.AlbumListViewModel
import com.google.gson.Gson
import java.net.URLEncoder

/**
 * Represents the navigation graph for the app.
 *
 * @param navController The [NavHostController] for navigation.
 * @param innerPadding The padding values for the inner content.
 */
@Composable
fun AppNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues = PaddingValues()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.AlbumList.route
    ) {
        composable(Screen.AlbumList.route) {
            val viewModel: AlbumListViewModel = hiltViewModel()
            AlbumListScreen(
                viewModel = viewModel,
                onAlbumClick = { album ->
                    val encodedJson = URLEncoder.encode(Gson().toJson(album), "utf-8")
                    navController.navigate(Screen.AlbumDetail.createRoute(encodedJson))
                },
                paddingValues = innerPadding
            )
        }

        composable(
            route = Screen.AlbumDetail.route,
            arguments = listOf(navArgument("album") { type = NavType.StringType })
        ) {
            val viewModel: AlbumDetailViewModel = hiltViewModel()
            val character by viewModel.album.collectAsState()

            character?.let {
                AlbumDetailScreen(
                    album = it,
                    onNavigateUp = { navController.navigateUp() },
                    paddingValues = innerPadding
                )
            }
        }
    }
}
