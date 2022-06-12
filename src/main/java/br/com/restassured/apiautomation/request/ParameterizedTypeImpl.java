package br.com.restassured.apiautomation.request;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

class ParameterizedTypeImpl implements ParameterizedType {

    private final ParameterizedType delegate;

    private final Type[] actualTypeArguments;

    ParameterizedTypeImpl(ParameterizedType delegate, Type[] actualTypeArguments) {
        this.delegate = delegate;
        this.actualTypeArguments = actualTypeArguments;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return actualTypeArguments;
    }

    @Override
    public Type getRawType() {
        return delegate.getRawType();
    }

    @Override
    public Type getOwnerType() {
        return delegate.getOwnerType();
    }
}
