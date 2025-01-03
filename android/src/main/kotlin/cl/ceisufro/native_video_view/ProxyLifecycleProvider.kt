package cl.ceisufro.native_video_view

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

class ProxyLifecycleProvider(activity: Activity) : Application.ActivityLifecycleCallbacks,
    LifecycleProvider {
    
    private val _lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this as LifecycleOwner)
    private val registrarActivityHashCode: Int = activity.hashCode()

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        if (activity.hashCode() != registrarActivityHashCode) {
            return
        }
        _lifecycleRegistry.handleLifecycleEvent(Event.ON_CREATE)
    }

    override fun onActivityStarted(activity: Activity) {
        if (activity.hashCode() != registrarActivityHashCode) {
            return
        }
        _lifecycleRegistry.handleLifecycleEvent(Event.ON_START)
    }

    override fun onActivityResumed(activity: Activity) {
        if (activity.hashCode() != registrarActivityHashCode) {
            return
        }
        _lifecycleRegistry.handleLifecycleEvent(Event.ON_RESUME)
    }

    override fun onActivityPaused(activity: Activity) {
        if (activity.hashCode() != registrarActivityHashCode) {
            return
        }
        _lifecycleRegistry.handleLifecycleEvent(Event.ON_PAUSE)
    }

    override fun onActivityStopped(activity: Activity) {
        if (activity.hashCode() != registrarActivityHashCode) {
            return
        }
        _lifecycleRegistry.handleLifecycleEvent(Event.ON_STOP)
    }

    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {
        if (activity.hashCode() != registrarActivityHashCode) {
            return
        }
        activity.application.unregisterActivityLifecycleCallbacks(this)
        _lifecycleRegistry.handleLifecycleEvent(Event.ON_DESTROY)
    }

    override fun getLifecycle(): Lifecycle? {
        return _lifecycleRegistry
    }

    init {
        activity.application.registerActivityLifecycleCallbacks(this)
    }
}