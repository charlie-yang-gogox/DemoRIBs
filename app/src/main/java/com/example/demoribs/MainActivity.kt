package com.example.demoribs

import android.view.ViewGroup
import com.example.demoribs.root.RootBuilder
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter

class MainActivity : RibActivity() {
    override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *> {
        val rootBuilder = RootBuilder(object : RootBuilder.ParentComponent {})
        return rootBuilder.build(parentViewGroup)
    }
}