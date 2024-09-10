package com.escalondev.movies_app_kmp.android.navigation.navigator

interface NavigationDestination {

    /**
     * get the route simpleName obtained from the Serialized destination class name.
     */
    val routeName: String
        get() = this.javaClass.simpleName
}
