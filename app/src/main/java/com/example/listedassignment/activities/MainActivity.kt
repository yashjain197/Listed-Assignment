package com.example.listedassignment.activities

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.listedassignment.adapter.FragmentPageAdapter
import com.example.listedassignment.api.ApiService
import com.example.listedassignment.api.RetrofitHelper
import com.example.listedassignment.databinding.ActivityMainBinding
import com.example.listedassignment.models.DataList
import com.example.listedassignment.models.MainViewModelFactory
import com.example.listedassignment.repository.DataRepository
import com.example.listedassignment.viewmodel.MainViewModel
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.material.tabs.TabLayout
import java.text.DateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    lateinit var dataList: DataList
    private lateinit var binding: ActivityMainBinding
    lateinit var fragmentAdapter: FragmentPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        mainViewModel.getApiCall()
        getLocalTimeGreeting()

    }

    private fun setFragment() {
        fragmentAdapter = FragmentPageAdapter(supportFragmentManager, lifecycle)
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Top Links"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Recent Links"))

        binding.viewPager2.adapter = fragmentAdapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        binding.viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })

    }

    fun init(){
        val randomImageService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val repository = DataRepository(randomImageService)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]
        mainViewModel.data.observe(this) {
            Log.d("MYDATA", it.status.toString())
            dataList = it
            setupGraphData()
            setFragment()
        }
    }


    private fun getLocalTimeGreeting() {
        //Get the time of day
        val date = Date()
        val cal = Calendar.getInstance()
        cal.time = date
        val hour = cal[Calendar.HOUR_OF_DAY]

        val ampm =  cal[Calendar.AM_PM]


        //Set greeting
            Log.d("DATEMO in when", ampm.toString()+" "+ hour)
            when (hour) {
                in 12..16 -> {
                    binding.GreetingMessage.text = "Good Afternoon";
                }
                in 17..20 -> {
                    binding.GreetingMessage.text = "Good Evening";
                }
                in 21..23 -> {
                    binding.GreetingMessage.text = "Good Night";
                }
                else -> {
                    binding.GreetingMessage.text = "Good Morning";
                }
            }

    }

    fun setupGraphData(){
        val des = Description()
        des.text = " "
        des.setPosition(150f, 15f)
        binding.lineChart.description = des
        binding.lineChart.axisRight.setDrawLabels(false)
        val xValues = listOf("2023-06-09", "2023-06-10", "2023-06-11", "2023-06-12", "2023-06-13", "2023-06-14")

        val xAxis = binding.lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = IndexAxisValueFormatter(xValues)
        xAxis.labelCount = xValues.size
        xAxis.granularity = 1f

        val yAxis: YAxis = binding.lineChart.axisLeft
        yAxis.axisMinimum = 0f
        yAxis.axisMaximum = 30f
        yAxis.axisLineWidth = 2f
        yAxis.axisLineColor = Color.BLACK
        yAxis.labelCount = 5

        var entries = ArrayList<com.github.mikephil.charting.data.Entry>()

        val yAxisData = dataList.data.overall_url_chart
        entries.add(com.github.mikephil.charting.data.Entry(0f, yAxisData.`2023-06-09`.toFloat()))
        entries.add(com.github.mikephil.charting.data.Entry(1f, yAxisData.`2023-06-10`.toFloat()))
        entries.add(com.github.mikephil.charting.data.Entry(2f, yAxisData.`2023-06-11`.toFloat()))
        entries.add(com.github.mikephil.charting.data.Entry(3f, yAxisData.`2023-06-12`.toFloat()))
        entries.add(com.github.mikephil.charting.data.Entry(4f, yAxisData.`2023-06-13`.toFloat()))
        entries.add(com.github.mikephil.charting.data.Entry(5f, yAxisData.`2023-06-14`.toFloat()))

        var dataSet = LineDataSet(entries, "Chart")
        dataSet.color = Color.BLUE

        var lineData = LineData(dataSet)

        binding.lineChart.data = lineData

        binding.lineChart.invalidate()



    }
}