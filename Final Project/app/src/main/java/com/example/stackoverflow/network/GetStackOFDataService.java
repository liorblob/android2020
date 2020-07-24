package com.example.stackoverflow.network;

import com.example.stackoverflow.model.StackOFResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetStackOFDataService {@GET("search")
Call<StackOFResult> getAnswers(
                @Query("order") String order,
                @Query("site") String site,
                @Query("sort") String sort,
                @Query("tagged") String tagged);
}
