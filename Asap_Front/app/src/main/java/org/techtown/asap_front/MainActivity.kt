package org.techtown.asap_front

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.techtown.asap_front.data_object.Comment1_Adapter


class MainActivity() : AppCompatActivity(){
    private lateinit var recyclerView1 : RecyclerView
    private lateinit var recyclerView2 : RecyclerView

    lateinit var jobPostListFragment: JobPostListFragment
    lateinit var empPostListFragment: EmpPostListFragment
    lateinit var myPageFragment: MyPageFragment

    private var user_id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView1 = findViewById(R.id.recyclerView_job)
        recyclerView1.layoutManager = GridLayoutManager(this, 1)
        val adapter = Comment1_Adapter()

        recyclerView2 = findViewById(R.id.recyclerView_emp)
        recyclerView2.layoutManager = GridLayoutManager(this, 1)
        val adapter2 = Comment1_Adapter()

        recyclerView1.adapter = adapter
        recyclerView2.adapter = adapter2

        user_id = intent.getStringExtra("user_id").toString()
        var login_ID = intent.getStringExtra("login_ID")
        var nickname = intent.getStringExtra("nickname")

        nav.setOnNavigationItemSelectedListener(onBottomNavItemSelectedListener)

        //기본화면
        jobPostListFragment = JobPostListFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragment_frame, jobPostListFragment).commit()
    }

    //네비게이션바 이벤트 -> 프레그먼트 이동
    private val onBottomNavItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.job ->{
                jobPostListFragment = JobPostListFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_frame, jobPostListFragment).commit()
            }
            R.id.emp ->{
                empPostListFragment = EmpPostListFragment.newInstance()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_frame, empPostListFragment).commit()
            }
            R.id.my ->{
                with(supportFragmentManager.beginTransaction()) {
                    val fragment3 = MyPageFragment()
                    val bundle = Bundle()
                    bundle.putString("userId", user_id)
                    fragment3.arguments = bundle
                    replace(R.id.fragment_frame, fragment3)
                    commit()
                }
            }
        }
        true
    }

}