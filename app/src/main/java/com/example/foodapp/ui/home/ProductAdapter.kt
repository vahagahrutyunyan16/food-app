package com.example.foodapp.ui.home


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R

class ProductAdapter(
    private val dataList: List<ProductItem>,
    val addToList: (itemPosition: Int) -> Unit,
    val removeFromList: (itemPosition: Int) -> Unit,
    val showDialog: () -> Unit,
    val onItemClicked: (ProductItem) -> Unit
) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    var count = 0
    var price = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.product_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]


        holder.name.text = item.name
        holder.price.text = "$ " + item.price.toString()
        holder.image.setImageResource(item.imageRes)
        price = item.price.toInt()

        if(item.isLiked) {
            holder.favorites.setColorFilter(holder.favorites.resources.getColor(R.color.red))
        } else {
            holder.favorites.setColorFilter(holder.favorites.resources.getColor(R.color.silver_sand))
        }

        holder.plus.setOnClickListener {
            showDialog()
            count++
            holder.productCount.text = count.toString()
            price+=price

            if (count > 0) {
                holder.minus.setColorFilter(holder.favorites.resources.getColor(R.color.ufo_green))
            } else {
                holder.minus.setColorFilter(holder.favorites.resources.getColor(R.color.silver_sand))
            }
        }


        holder.minus.setOnClickListener {
            showDialog()

            if (count > 0) {
                count--
                price-=price
                holder.productCount.text = count.toString()
            }
            if (count > 0) {
                holder.minus.setColorFilter(holder.favorites.resources.getColor(R.color.ufo_green))
            } else {
                holder.minus.setColorFilter(holder.favorites.resources.getColor(R.color.silver_sand))
            }
        }

        holder.favorites.setOnClickListener {
            if(item.isLiked) {
                holder.favorites.setColorFilter(holder.favorites.resources.getColor(R.color.silver_sand))
                removeFromList.invoke(position)
            } else {
                holder.favorites.setColorFilter(holder.favorites.resources.getColor(R.color.red))
                addToList.invoke(position)
            }
        }

        holder.product.setOnClickListener {
            onItemClicked.invoke(item)
//            it.findNavController().navigate(R.id.productFragment)

            val result = "result"
//            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvName)
        val price: TextView = itemView.findViewById(R.id.tvPrice)
        val image: ImageView = itemView.findViewById(R.id.ivImage)
        val favorites: ImageView = itemView.findViewById(R.id.ivFavorites)
        val plus: ImageView = itemView.findViewById(R.id.ivPlus)
        val minus: ImageView = itemView.findViewById(R.id.ivMinus)
        val productCount: TextView = itemView.findViewById(R.id.tvCount)
        val product: ConstraintLayout = itemView.findViewById(R.id.clProduct)
    }
}

