package com.mobile.shopaid.app

import android.app.Application
import com.mobile.shopaid.R
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 * Created by: Petar Anastasov
 * On: 08/06/2018
 * At: 22:31
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/AvenirNext.otf")
                .setFontAttrId(R.attr.fontPath)
                .build())
    }
}