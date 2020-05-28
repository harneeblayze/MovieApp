package ng.com.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ng.com.view.MovieFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager.beginTransaction().replace(R.id.movie_content, MovieFragment()).commit()
    }


}
