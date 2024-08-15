package com.juanca.exploreit.ui.Places.DetailPlaces

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.juanca.exploreit.R
import com.juanca.exploreit.databinding.FragmentDetailPlaceBinding
import com.juanca.exploreit.models.Places
import com.squareup.picasso.Picasso
import kotlin.properties.Delegates


class DetailPlaceFragment : Fragment(R.layout.fragment_detail_place), OnMapReadyCallback {

    lateinit var binding: FragmentDetailPlaceBinding
    private lateinit var map: GoogleMap
    private lateinit var globalView: View
    private lateinit var place: Places
    private var lat by Delegates.notNull<Double>()
    private var long by Delegates.notNull<Double>()
    private lateinit var title: String
    private val viewModel: DetailPlaceViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailPlaceBinding.bind(view)
        globalView = view

        init()
        observers()
        events()
        createMapFragment()
    }

    private fun createMapFragment() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun init(){
        arguments?.let {
            place = DetailPlaceFragmentArgs.fromBundle(it).objPlace
            setDetail(place)
        }
    }

    private fun events() = with(binding){
        includeHeader.btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setDetail(place: Places) {
        viewModel.setPlace(place)
    }

    private fun observers() = with(binding){
        viewModel.place.observe(viewLifecycleOwner){
            includeHeader.TitleBar.text = it.titulo
            tvDescCorta.text = it.descripcionCorta
            tvDescDetallada.text = it.descripcionDetallada
            tvDireccion.text = it.direccion
            Picasso.get().load(it.cabeceraImagen).error(R.drawable.img_not_found)
                .into(imgDetailPlace)
            lat = it.Lat
            long = it.Lon
            title = it.titulo
        }
    }

    override fun onMapReady(map: GoogleMap) {
        this.map = map

        // Realiza la búsqueda geocodificada del lugar
        createMarker()
    }

    private fun createMarker(){
        val coordinates = LatLng(lat, long)
        val marker = MarkerOptions().position(coordinates).title(title)
        this.map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates, 18f),
            2000,
            null
        )
    }
}