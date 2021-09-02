package com.nisa.newsappid.data.repository

import com.nisa.newsappid.data.model.ResponseNews
import com.nisa.newsappid.data.source.APIService
import com.nisa.newsappid.utils.Constant
import com.nisa.newsappid.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject


//menggunakan di untuk menginject api kedalam news repository
class NewsRepository @Inject constructor(private val apiService: APIService){

    suspend fun fetchNewsRepository(): Flow<Resource<ResponseNews>> {
        //flow = asyncronous programming exclusif buat kotlin (flow termasuk coroutine)
        return flow {
            val response = apiService.fetchNews(
                Constant.COUNTRY,
                Constant.CATEGORY,
                Constant.API_KEY
            )

            //bisnis logic
            try {
                //kalo sukses kirim state sukses
                if(response.articles.isNotEmpty()){
                    emit(Resource.Success(response))
                }else{
                    //kalo empty kirim state empty
                    emit(Resource.Empty)
                }
            }catch (e: HttpException){
                //kalo gagal kirim state error
                emit(Resource.Error(e.toString(), response))
            }
        }
    }
}