package com.juanca.exploreit.ui.Places.ListPlaces

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.transition.Visibility
import com.juanca.exploreit.R
import com.juanca.exploreit.databinding.FragmentListPlacesBinding
import com.juanca.exploreit.databinding.ItemPlaceBinding
import com.juanca.exploreit.models.Places
import com.juanca.exploreit.utils.BaseAdapter
import com.juanca.exploreit.utils.Toast.Toast
import com.squareup.picasso.BuildConfig
import com.squareup.picasso.Picasso


class ListPlacesFragment : Fragment(R.layout.fragment_list_places) {

    lateinit var binding: FragmentListPlacesBinding
    private lateinit var globalView: View
    private val viewmodel: ListPlacesViewModel by viewModels()


    private val adapter: BaseAdapter<Places> =
        object : BaseAdapter<Places>(emptyList()) {
            override fun getViewHolder(parent: ViewGroup): BaseViewHolder<Places> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_place, parent, false)
                return object : BaseViewHolder<Places>(view) {
                    private val binding: ItemPlaceBinding = ItemPlaceBinding.bind(view)

                    override fun bind(entity: Places) = with(binding) {
                        tvNamePlace.text = entity.titulo
                        tvDetalle.text = entity.descripcionCorta
                        Picasso.get().load(entity.cabeceraImagen).error(R.drawable.img_not_found)
                            .into(imgPlace)

                        binding.root.setOnClickListener{
                            sendDetailPlace(entity)
                        }
                    }
                }
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListPlacesBinding.bind(view)
        globalView = view
        setUpAdapter()
        init()
        observers()
    }

    private fun init(){
        viewmodel.getPlaces()
    }

    private fun observers(){
        viewmodel.loader.observe(viewLifecycleOwner){
            if (it) binding.pbListPlaces.visibility = View.VISIBLE
            else binding.pbListPlaces.visibility = View.GONE
        }
        viewmodel.error.observe(viewLifecycleOwner){
            requireContext().Toast(it)
        }
        viewmodel.message.observe(viewLifecycleOwner){
            requireContext().Toast(it)
        }
        viewmodel.places.observe(viewLifecycleOwner){
            adapter.updateList(it)
            saveDataBase(it)
        }
    }

    private fun saveDataBase(places: List<Places>) {
        viewmodel.savePLacesBd(places)
    }

    private fun setUpAdapter() = with(binding){
        rcvPlaces.adapter = adapter
    }

    private fun sendDetailPlace(place: Places){
        val direction = ListPlacesFragmentDirections.actionListPlacesFragmentToDetailPlaceFragment(place)
        Navigation.findNavController(globalView).navigate(direction)
    }
}