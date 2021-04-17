package id.conversion.app.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.conversion.app.ui.currency.CurrencyFragment
import id.conversion.app.ui.currency.CurrencyModule
import id.conversion.app.ui.distance.DistanceFragment
import id.conversion.app.ui.distance.DistanceModule
import id.conversion.app.ui.time.TimeFragment
import id.conversion.app.ui.time.TimeModule
import id.conversion.app.ui.volume.VolumeFragment
import id.conversion.app.ui.volume.VolumeModule
import id.conversion.app.ui.weight.WeightFragment
import id.conversion.app.ui.weight.WeightModule

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = [WeightModule::class])
    abstract fun contributeWeightFragment(): WeightFragment

    @ContributesAndroidInjector(modules = [DistanceModule::class])
    abstract fun contributeDistanceFragment(): DistanceFragment

    @ContributesAndroidInjector(modules = [VolumeModule::class])
    abstract fun contributeVolumeFragment(): VolumeFragment

    @ContributesAndroidInjector(modules = [TimeModule::class])
    abstract fun contributeTimeFragment(): TimeFragment

    @ContributesAndroidInjector(modules = [CurrencyModule::class])
    abstract fun contributeCurrencyFragment(): CurrencyFragment

}