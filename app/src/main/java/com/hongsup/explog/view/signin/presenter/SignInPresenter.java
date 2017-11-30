package com.hongsup.explog.view.signin.presenter;

import android.util.Log;

import com.hongsup.explog.data.sign.SignIn;
import com.hongsup.explog.data.sign.source.SignRepository;
import com.hongsup.explog.view.signin.contract.SignInContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by Android Hong on 2017-11-30.
 */

public class SignInPresenter implements SignInContract.iPresenter {

    private SignInContract.iView view;
    private SignRepository repository;

    public SignInPresenter() {
        repository = SignRepository.getInstance();
    }

    @Override
    public void attachView(SignInContract.iView view) {
        this.view = view;
    }

    @Override
    public void getSignIn(SignIn signIn) {
        view.showProgress();

        /**
         * Observable Pattern 으로 한 경우
         */
        Observable<Response<SignIn>> observable = repository.signIn(signIn);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    // next
                    Log.e("SignInActivity", data.code() + ", " + data.message());
                    if(data.isSuccessful()){
                        if (data.code() == 200) {
                            view.hideProgress();
                            /**
                             * 값을 넘겨주거나, SharedPreference 에 Token, Email, img_path, NickName 을 저장한다.
                             */
                            view.goMain();
                        }
                    }else{
                        Log.e("SignInActivity", "getSignIn: Error");
                    }

                }, throwable -> {
                    Log.e("SignInActivity", throwable.getMessage());
                    // error;
                    view.hideProgress();
                    view.showError();
                });
    }
}