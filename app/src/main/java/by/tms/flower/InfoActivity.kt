package by.tms.flower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val name = intent.getStringExtra(REQUEST_FIELD_FLOWER_NAME)
        val price = intent.getDoubleExtra(REQUEST_FIELD_FLOWER_PRICE, 0.0)
        val url = intent.getStringExtra(REQUEST_FIELD_FLOWER_URL)

        nameInfo.text = name
        priceInfo.text = price.toString()
        Picasso.get().load(url)
            .placeholder(R.mipmap.ic_launcher)
            .into(imageInfo)
    }
}
