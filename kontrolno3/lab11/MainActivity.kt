package com.example.lab11

import android.R.attr.onClick
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab11.models.Place
import com.example.lab11.ui.theme.Lab11Theme
import com.example.lab11.viewmodel.PlacesViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val vm: PlacesViewModel by viewModels ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab11Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        vm,
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(vm: PlacesViewModel,name :String, modifier: Modifier = Modifier) {

    val scope = rememberCoroutineScope()
    val places by vm.places.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Button(onClick ={
            scope.launch {vm.addItem()}
        })
        {Text("Add Item")}
        Button(onClick ={
            scope.launch {vm.deleteItem()}
        })
        {Text("delete Item")}
        Button(onClick ={
            scope.launch {vm.updateItem()}
        })
        {Text("update Item")}
        PlaceVerticalGrid(places,Modifier.padding(bottom = 8.dp))
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab11Theme {

    }
}
@Composable
fun PlaceVerticalGrid(places: List<Place>, modifier: Modifier = Modifier){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(places){ place ->
            PlaceCard(
                place = place,
                modifier = modifier.padding(8.dp)
            )

        }
    }
}
@Composable fun PlaceCard(place: Place, modifier: Modifier = Modifier){
    Card(modifier = modifier){
        Column {
            Image(
                painter = painterResource(id= place.drawableResourceId),
                contentDescription = stringResource(id = place.stringResourceId),
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxWidth().height(194.dp)
            )
            Text(
                text = stringResource(id = place.stringResourceId),
                style = MaterialTheme.typography.headlineSmall,
                modifier = modifier.padding(16.dp).height(200.dp)
            )
        }
    }

}