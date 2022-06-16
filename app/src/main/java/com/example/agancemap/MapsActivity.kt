package com.example.agancemap

import BitmapHelper
import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agancemap.databinding.ActivityMapsBinding
import com.example.agancemap.models.InwiPosition
import com.example.agancemap.models.Place
import com.example.agancemap.models.PlacesReader
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.bottomsheet.BottomSheetDialog


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var circle: Circle? = null
    private val places: List<InwiPosition> by lazy {
        PlacesReader(this).read()
    }
    private val bicycleIcon: BitmapDescriptor by lazy {
        val color = ContextCompat.getColor(this, R.color.magento)
        BitmapHelper.vectorToBitmap(this, R.drawable.ic_baseline_location_on_24, color)
    }
    private val adapterPlaces:AdapterPlaces by lazy {
        AdapterPlaces(this, listOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }
    fun resetView(){
        binding.notfound.visibility=View.GONE

    }

    fun initComponent(googleMap: GoogleMap){
        binding.search.imeOptions = EditorInfo.IME_ACTION_DONE
        binding.search.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            resetView()
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                var city=getInwiposition(binding.search.text.toString())
                if(!city.isEmpty()){
                    setMarkerToMap(googleMap = googleMap,city)
                    var layoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
                    binding.places.layoutManager = layoutManager
                    adapterPlaces.setListPlaces(city)

                    binding.places.visibility=View.VISIBLE
                    binding.places.adapter=adapterPlaces
                }else{
                    binding.notfound.visibility=View.VISIBLE
                    binding.places.visibility=View.GONE

                }


                return@OnEditorActionListener true
            }
            false
        })
        binding.search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                binding.cancel.visibility= View.VISIBLE
                binding.filters.visibility=View.GONE
                binding.search.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_launcher_foreground_magento, 0, 0, 0)
            }
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                Log.d("00000", "onTextChanged: "+s)


            }
        })

        binding.cancel.setOnClickListener {

            binding.cancel.visibility= View.GONE
            binding.filters.visibility=View.VISIBLE
            binding.search.text.clear()
            binding.search.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_launcher_foreground, 0, 0, 0)
            hideKeyboard(this)
        }
    }
    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    fun getInwiposition(city:String):List<InwiPosition>{
      return  places.filter { it.city.labelFr.toLowerCase().contains(city) }
    }

    fun setMarkerToMap(googleMap: GoogleMap,places: List<InwiPosition>){


        mMap = googleMap
       // mMap.setInfoWindowAdapter(MarkerInfoWindowAdapter(this))
        mMap.setOnMarkerClickListener(this)
        // Add a marker in Sydney and move the camera
        val maroc = LatLng(31.791702, -7.092620)
        // addClusteredMarkers(mMap)


        places.forEach { place ->

            var latLng=LatLng(place.latitude.toDouble(),place.logitude.toDouble())
            mMap.addMarker(
                MarkerOptions()
                    .title(place.label)
                    .position(latLng)
                    .icon(bicycleIcon)
            )?.tag=place

        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(maroc,6F))

    }
    override fun onMapReady(googleMap: GoogleMap) {
        initComponent(googleMap)

      /*  mMap = googleMap
        mMap.setInfoWindowAdapter(MarkerInfoWindowAdapter(this))

        // Add a marker in Sydney and move the camera
        val maroc = LatLng(31.791702, -7.092620)
       // addClusteredMarkers(mMap)


       places.forEach { place ->

           var latLng=LatLng(place.latitude.toDouble(),place.logitude.toDouble())
            mMap.addMarker(
                MarkerOptions()
                    .title(place.label)
                    .position(latLng)
                    .icon(bicycleIcon)
            )?.tag=place

        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(maroc,6F))

    }*/
    /*private fun addClusteredMarkers(googleMap: GoogleMap) {
        // Create the ClusterManager class and set the custom renderer.
        val clusterManager = ClusterManager<Place>(this, googleMap)
        clusterManager.renderer =
            PlaceRenderer(
                this,
                googleMap,
                clusterManager
            )

        // Set custom info window adapter
        clusterManager.markerCollection.setInfoWindowAdapter(MarkerInfoWindowAdapter(this))

        // Add the places to the ClusterManager.
        clusterManager.addItems(places)
        clusterManager.cluster()

        // Set ClusterManager as the OnCameraIdleListener so that it
        // can re-cluster when zooming in and out.
        googleMap.setOnCameraIdleListener {
            clusterManager.onCameraIdle()
            clusterManager.setOnClusterItemClickListener { item ->
                addCircle(googleMap, item)
                return@setOnClusterItemClickListener false
            }
        }*/
    }
    private fun addCircle(googleMap: GoogleMap, item: Place) {
        circle?.remove()
        circle = googleMap.addCircle(
            CircleOptions()
                .center(item.latLng)
                .radius(1000.0)
                .fillColor(ContextCompat.getColor(this, android.R.color.transparent))
                .strokeColor(ContextCompat.getColor(this, R.color.purple_500))
        )
    }

    private fun showBottomSheet():BottomSheetDialog{
        var dialog= BottomSheetDialog(this)
        dialog.setContentView(R.layout.detail_agence_bottomsheet)

        return dialog
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        var marker=p0
        var inwiPosition= marker.tag
        Log.d("0000", "onMarkerClick: ")

        showBottomSheet().show()
        return false
    }
}