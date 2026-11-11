
package com.example.notificationmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay

val Blue = Color(0xFF4361EE)
val Background = Color(0xFFF7F7FB)

// MODEL
data class NotificationItem(
    val title: String,
    val message: String,
    val time: String
)

// VIEWMODEL
class NotificationViewModel : ViewModel() {

    var notifications by mutableStateOf(
        listOf(
            NotificationItem(
                "Sắp đến hạn",
                "Còn 3 công việc cần thực hiện lúc 14:00.",
                "13:45"
            ),
            NotificationItem(
                "Đã quá hạn",
                "Bạn chưa hoàn thành công việc",
                "12:10"
            ),
            NotificationItem(
                "Làm tốt lắm!",
                "Bạn đã xong 2 việc trong sáng nay.",
                "11:30"
            ),
            NotificationItem(
                "Nhắc việc",
                "Họp nhóm đồ án trong tuần này.",
                "Hôm qua"
            )
        )
    )
        private set

    fun clearNotifications() {
        notifications = emptyList()
    }
}

// MAIN ACTIVITY
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainScreen()
            }
        }
    }
}

// MAIN SCREEN
@Composable
fun MainScreen(
    vm: NotificationViewModel = viewModel()
) {
    var selectedTab by remember {
        mutableStateOf("Thông báo")
    }

    LaunchedEffect(vm) {
        delay(10000)
        vm.clearNotifications()
    }

    Scaffold(
        containerColor = Background,
        bottomBar = {
            BottomNavigation(
                selectedTab = selectedTab,
                onSelect = { selectedTab = it }
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (selectedTab) {
                "Thông báo" -> NotificationScreen(vm)
                "Trang chủ" -> SimpleScreen("Trang chủ")
                "Danh sách" -> SimpleScreen("Danh sách")
                "Cài đặt" -> SimpleScreen("Cài đặt")
                "Thêm" -> SimpleScreen("Thêm việc cần làm")
            }
        }
    }
}

// NOTIFICATION SCREEN
@Composable
fun NotificationScreen(vm: NotificationViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 20.dp,
                    bottom = 15.dp
                ),
            horizontalArrangement =
                Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Thông báo",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF171923)
            )

            if (vm.notifications.isNotEmpty()) {
                TextButton(
                    onClick = {
                        vm.clearNotifications()
                    }
                ) {
                    Text(
                        "Đánh dấu đã đọc",
                        color = Blue,
                        fontSize = 12.sp
                    )
                }
            }
        }

        if (vm.notifications.isEmpty()) {
            EmptyView()
        } else {
            LazyColumn(
                contentPadding =
                    PaddingValues(horizontal = 16.dp),
                verticalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {
                items(vm.notifications) { item ->
                    NotificationCard(item)
                }
            }
        }
    }
}

// NOTIFICATION CARD
@Composable
fun NotificationCard(item: NotificationItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .background(
                        Color(0xFFEAF0FF),
                        RoundedCornerShape(10.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector =
                        Icons.Outlined.Notifications,
                    contentDescription = null,
                    tint = Blue,
                    modifier = Modifier.size(21.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color(0xFF20232A)
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = item.message,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    color = Color.Gray
                )
            }

            Text(
                text = item.time,
                fontSize = 10.sp,
                color = Color.Gray
            )
        }
    }
}

// EMPTY VIEW
@Composable
fun EmptyView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(110.dp)
                .background(
                    Color(0xFFE5EBFF),
                    CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(75.dp)
                    .background(
                        Color.White,
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector =
                        Icons.Outlined.Notifications,
                    contentDescription = null,
                    tint = Blue,
                    modifier = Modifier.size(38.dp)
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(6.dp)
                    .size(28.dp)
                    .background(
                        Color(0xFF48A66A),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(17.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Chưa có thông báo nào",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF20232A)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Khi có việc sắp đến hạn, quá hạn hoặc\n" +
                    "lời nhắc, bạn sẽ thấy chúng ở đây.",
            fontSize = 13.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            lineHeight = 19.sp
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {},
            shape = RoundedCornerShape(9.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue
            ),
            contentPadding = PaddingValues(
                horizontal = 20.dp,
                vertical = 12.dp
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = "Thêm việc cần làm",
                fontSize = 13.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = {}) {
            Text(
                text = "Cài đặt nhắc việc",
                color = Blue,
                fontSize = 12.sp
            )
        }
    }
}

// BOTTOM NAVIGATION
@Composable
fun BottomNavigation(
    selectedTab: String,
    onSelect: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .navigationBarsPadding()
            .height(65.dp)
            .padding(horizontal = 8.dp),
        horizontalArrangement =
            Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavigationItem(
            "Trang chủ",
            Icons.Outlined.Home,
            selectedTab,
            onSelect
        )

        NavigationItem(
            "Danh sách",
            Icons.Outlined.List,
            selectedTab,
            onSelect
        )

        Box(
            modifier = Modifier
                .size(52.dp)
                .shadow(6.dp, CircleShape)
                .background(Blue, CircleShape)
                .clickable {
                    onSelect("Thêm")
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Thêm",
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }

        NavigationItem(
            "Thông báo",
            Icons.Outlined.Notifications,
            selectedTab,
            onSelect
        )

        NavigationItem(
            "Cài đặt",
            Icons.Outlined.Settings,
            selectedTab,
            onSelect
        )
    }
}

@Composable
fun NavigationItem(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    selectedTab: String,
    onSelect: (String) -> Unit
) {
    val isSelected = selectedTab == title

    Column(
        modifier = Modifier
            .clickable {
                onSelect(title)
            }
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = if (isSelected) Blue else Color.Gray,
            modifier = Modifier.size(22.dp)
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = title,
            fontSize = 9.sp,
            color = if (isSelected) Blue else Color.Gray,
            fontWeight = if (isSelected)
                FontWeight.Bold
            else
                FontWeight.Normal
        )
    }
}

// OTHER SCREENS
@Composable
fun SimpleScreen(title: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
