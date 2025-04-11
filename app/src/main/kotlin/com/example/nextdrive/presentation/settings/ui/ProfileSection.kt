package com.example.nextdrive.presentation.settings.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.nextdrive.utils.toImageBitmap

import com.example.nextdrive.presentation.settings.SettingsViewModel


@Composable
fun ProfileSection(
    viewModel: SettingsViewModel,
    onProfileClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onProfileClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (viewModel.avatarBytes != null) {
                    Image(
                        bitmap = viewModel.avatarBytes!!.toImageBitmap(),
                        contentDescription = "User Avatar",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile",
                        modifier = Modifier.size(48.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "${viewModel.userProfile.firstName} ${viewModel.userProfile.lastName}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = viewModel.userEmail,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Go to profile",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}


//@Composable
//fun ProfileSection(
//    viewModel: SettingsViewModel,
//    onProfileClick: () -> Unit
//) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 16.dp, vertical = 8.dp)
//            .clickable { onProfileClick() },
//        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
//    ) {
//        Row(
//            modifier = Modifier.padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Box(
//                modifier = Modifier.size(64.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                if (viewModel.avatarBytes != null) {
//                    Image(
//                        bitmap = viewModel.avatarBytes!!.toImageBitmap(),
//                        contentDescription = "User Avatar",
//                        modifier = Modifier.fillMaxSize(),
//                        contentScale = ContentScale.Crop
//                    )
//                } else {
//                    Icon(
//                        imageVector = Icons.Default.Person,
//                        contentDescription = "Profile",
//                        modifier = Modifier.size(48.dp)
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.width(16.dp))
//
//            Column(
//                modifier = Modifier.weight(1f)
//            ) {
//                Text(
//                    text = "${viewModel.userProfile.firstName} ${viewModel.userProfile.lastName}",
//                    style = MaterialTheme.typography.titleMedium
//                )
//                Text(
//                    text = viewModel.userEmail,
//                    style = MaterialTheme.typography.bodyMedium,
//                    color = MaterialTheme.colorScheme.onSurfaceVariant
//                )
//            }
//
//            Icon(
//                imageVector = Icons.Default.ChevronRight,
//                contentDescription = "Go to profile",
//                tint = MaterialTheme.colorScheme.onSurfaceVariant
//            )
//        }
//    }
//}