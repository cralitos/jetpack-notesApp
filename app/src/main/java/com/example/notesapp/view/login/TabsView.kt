package com.example.notesapp.view.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TabsView(innerPadding: PaddingValues) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Login", "Register")

    Column(modifier = Modifier.padding(innerPadding)) {
        TabRow(selectedTabIndex = selectedTab,
            contentColor = Color.Black,
            containerColor = Color.White,
            indicator = {
                tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab])
                )
            }) {
            tabs.forEachIndexed { index, title ->
                Tab(selected=selectedTab == index, onClick = { selectedTab = index }) {
                    Text(text = title)
                }
            }
        }
        //vistas del tab
        when(selectedTab) {
            0 -> LoginView()
            1 -> RegisterView()
        }
    }
}


