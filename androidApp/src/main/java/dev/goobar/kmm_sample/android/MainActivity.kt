package dev.goobar.kmm_sample.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.goobar.kmm_sample.Greeting
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import dev.goobar.kmm_sample.PokemonNetworkClient
import dev.goobar.kmm_sample.ui.PokemonPresenter
import kotlinx.coroutines.flow.collect

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()

        val presenter = PokemonPresenter()

        lifecycleScope.launchWhenCreated {
            presenter.state.collect {
                tv.text = it.map{ it.name }.fold("") { next, acc -> "$next, $acc"}
            }
        }
    }
}
