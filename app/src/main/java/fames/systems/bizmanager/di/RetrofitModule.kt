package fames.systems.bizmanager.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fames.systems.bizmanager.application.auth.data.AuthAPI
import fames.systems.bizmanager.application.clients.data.ClientsAPI
import fames.systems.bizmanager.application.dashboard.data.DashboardAPI
import fames.systems.bizmanager.application.products.data.ProductsAPI
import fames.systems.bizmanager.application.settings.data.SettingsAPI
import fames.systems.bizmanager.application.tpvpos.data.TpvPosAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private const val BASE_URL = "https://bizmanager.herokuapp.com/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthAPI = retrofit.create(AuthAPI::class.java)

    @Singleton
    @Provides
    fun provideDashboardApi(retrofit: Retrofit): DashboardAPI = retrofit.create(DashboardAPI::class.java)

    @Singleton
    @Provides
    fun provideClientsApi(retrofit: Retrofit): ClientsAPI = retrofit.create(ClientsAPI::class.java)

    @Singleton
    @Provides
    fun provideTpsPosApi(retrofit: Retrofit): TpvPosAPI = retrofit.create(TpvPosAPI::class.java)

    @Singleton
    @Provides
    fun provideProductsApi(retrofit: Retrofit): ProductsAPI = retrofit.create(ProductsAPI::class.java)

    @Singleton
    @Provides
    fun provideSettingsApi(retrofit: Retrofit): SettingsAPI = retrofit.create(SettingsAPI::class.java)
}