package com.borsch.shift.cft.network;

import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.borsch.shift.cft.exception.EmptyBodyException;
import com.borsch.shift.cft.exception.ExceptionConst;
import com.borsch.shift.cft.features.borsch.domain.model.Wrapper;

public final class DefaultCallback<T> implements Callback<Wrapper<T>> {

    private final Carry<T> carry;

    public DefaultCallback(final Carry<T> carry) {
        this.carry = carry;
    }

    @Override
    public void onResponse(Call<Wrapper<T>> call, Response<Wrapper<T>> response) {
        Wrapper<T> wrapper = response.body();
        if (wrapper != null) {
            if(wrapper.getStatus().equals("OK")){
                carry.onSuccess(wrapper.getData());
            }else{
                carry.onFailure(new ExceptionConst(wrapper.getMessage()));
            }
        } else {
            carry.onFailure(new EmptyBodyException());
        }
    }

    @Override
    public void onFailure(Call<Wrapper<T>> call, Throwable t) {
        carry.onFailure(t);
    }

}