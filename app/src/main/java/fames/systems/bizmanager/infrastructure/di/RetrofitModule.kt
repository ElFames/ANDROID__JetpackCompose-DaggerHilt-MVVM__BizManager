package fames.systems.bizmanager.infrastructure.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fames.systems.bizmanager.application.auth.infrastructure.AuthAPI
import fames.systems.bizmanager.application.clients.infrastructure.ClientsAPI
import fames.systems.bizmanager.application.dashboard.infrastructure.DashboardAPI
import fames.systems.bizmanager.application.products.infrastructure.ProductsAPI
import fames.systems.bizmanager.application.settings.infrastructure.SettingsAPI
import fames.systems.bizmanager.application.tpvpos.infrastructure.TpvPosAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private const val BASE_URL = "https://bizmanager.herokuapp.com/"

    @Singleton
    @Provides
    fun provideRetrofit() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideAuthApi(retrofit: Retrofit) = retrofit.create(AuthAPI::class.java)

    @Singleton
    @Provides
    fun provideDashboardApi(retrofit: Retrofit) = retrofit.create(DashboardAPI::class.java)

    @Singleton
    @Provides
    fun provideClientsApi(retrofit: Retrofit) = retrofit.create(ClientsAPI::class.java)

    @Singleton
    @Provides
    fun provideTpsPosApi(retrofit: Retrofit) = retrofit.create(TpvPosAPI::class.java)

    @Singleton
    @Provides
    fun provideProductsApi(retrofit: Retrofit) = retrofit.create(ProductsAPI::class.java)

    @Singleton
    @Provides
    fun provideSettingsApi(retrofit: Retrofit) = retrofit.create(SettingsAPI::class.java)
}