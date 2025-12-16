package com.example.kontrolno3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kontrolno3.models.Course
import com.example.kontrolno3.ui.theme.Kontrolno3Theme
import com.example.kontrolno3.viewmodel.CoursesViewModel

class MainActivity : ComponentActivity() {
    private val vm: CoursesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kontrolno3Theme {
                val courses by vm.courses.collectAsState()
                CourseGrid(courses)
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun CourseGridPreview() {
    val sampleCourses = listOf(
        Course(R.string.place1, R.drawable.image1, 300),
        Course(R.string.place2, R.drawable.image2, 300),
        Course(R.string.place3, R.drawable.image3, 300),
        Course(R.string.place4, R.drawable.image4, 300),
    )

    Kontrolno3Theme {
        CourseGrid(courses = sampleCourses)
    }
}
@Composable
fun CourseGrid(courses: List<Course>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(courses) { course ->
            CourseCard(course)
        }
    }
}


@Composable
fun CourseCard(course: Course) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {

            // IMAGE — 65x65, touches top, left, bottom
            Image(
                painter = painterResource(course.imageResId),
                contentDescription = stringResource(course.nameResId),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(65.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 16.dp,
                            bottomStart = 16.dp
                        )
                    )
            )

            // TEXT CONTENT
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(
                        start = 16.dp, // 🔥 THIS aligns text + icon 16dp from image
                        end = 16.dp,
                        top = 8.dp,
                        bottom = 8.dp
                    ),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                // COURSE NAME
                Text(
                    text = stringResource(course.nameResId),
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // ICON + NUMBER — aligned with text (16dp from image)
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_launcher_background),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = course.freePlaces.toString(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
