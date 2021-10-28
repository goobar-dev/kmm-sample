package dev.goobar.kmm_sample.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.goobar.kmm_sample.Greeting
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import dev.goobar.kmm_sample.PokemonNetworkClient
import dev.goobar.kmm_sample.ui.PokemonPresenter
import kotlinx.coroutines.flow.collect

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : ComponentActivity() {

    private val presenter = PokemonPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Define a MutableState variable that will maintain state across composable invocations
            val pokemonList = remember { mutableStateListOf<String>()}

            // Load the data from presenter when the composable is first invoked
            LaunchedEffect(presenter) {
                presenter.state.collect {
                    pokemonList.addAll(it.map { it.name })
                }
            }

            // Start drawing out list of Pokemon
            PokemonList(pokemonList)
        }
    }
}

@Composable
private fun PokemonList(names: List<String>) {
    val context = LocalContext.current
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        names.forEach { pokemonName ->
            Text(
                text = pokemonName,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        Toast.makeText(context, pokemonName, Toast.LENGTH_SHORT).show()
                    }
                ,
                style = MaterialTheme.typography.h3
            )
        }
    }
}
