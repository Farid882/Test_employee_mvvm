package com.example.testemployeemvvm.screens.employees;

import com.example.testemployeemvvm.api.ApiFactory;
import com.example.testemployeemvvm.api.ApiService;
import com.example.testemployeemvvm.pojo.EmployeeResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmployeeListPresenter {
    private CompositeDisposable compositeDisposable;
    private EmployeeListView view;

    public EmployeeListPresenter(EmployeeListView view) {
        this.view = view;
    }

    public void loadData(){
        ApiFactory apiFactory=ApiFactory.getInstance();
        ApiService apiService=apiFactory.getApiService();
        compositeDisposable=new CompositeDisposable();
        Disposable disposable=apiService.getEmployees()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EmployeeResponse>() {
                    @Override
                    public void accept(EmployeeResponse employeeResponse) throws Exception {
                       view.loadData(employeeResponse.getResponse());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError();
                    }
                });
        compositeDisposable.add(disposable);
    }
    public void disposeDisposable(){
        if(compositeDisposable!=null){
            compositeDisposable.dispose();
        }
    }
}
