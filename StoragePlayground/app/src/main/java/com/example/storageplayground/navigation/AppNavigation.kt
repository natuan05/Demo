package com.example.storageplayground.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.storageplayground.screens.DataStoreScreen
import com.example.storageplayground.screens.FileScreen
import com.example.storageplayground.screens.FirebaseScreen
import com.example.storageplayground.screens.HomeScreen
import com.example.storageplayground.screens.NetworkScreen
import com.example.storageplayground.screens.RoomScreen

// Định nghĩa các route cho các màn hình
object Routes {
    const val HOME = "home"
    const val DATASTORE = "datastore"
    const val ROOM = "room"
    const val FILE = "file"
    const val NETWORK = "network"
    const val FIREBASE = "firebase" // Bonus cho Firebase
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HOME) {
        // Màn hình chính
        composable(Routes.HOME) {
            HomeScreen(navController = navController)
        }

        // Các màn hình demo
        composable(Routes.DATASTORE) {
            DataStoreScreen(navController = navController)
        }
        composable(Routes.ROOM) {
            RoomScreen(navController = navController)
        }
        composable(Routes.FILE) {
            FileScreen(navController = navController)
        }
        composable(Routes.NETWORK) {
            NetworkScreen(navController = navController)
        }
        composable(Routes.FIREBASE) {
            FirebaseScreen(navController = navController)
        }
    }
}