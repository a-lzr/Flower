package by.tms.flower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_flower_layout.*

class FlowerFragment : Fragment() {
    var name: String? = null
    var price: Double? = null
    var url: String? = null

    @Override
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_flower_layout, container, false)
    }

    @Override
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.nameFragment.text = name.toString()
        this.priceFragment.text = price.toString()
        Picasso.get().load(url)
            .placeholder(R.mipmap.ic_launcher)
            .into(this.imageFragment)
    }

    fun updateData(flower: Flower) {
        this.name = flower.name
        this.price = flower.price
        this.url = flower.url
    }
}