package com.example.di;

import com.example.api.OrcidResource;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {OrcidValidatorModule.class})
public interface AppComponent {
    OrcidResource getOrcidResource();
}
