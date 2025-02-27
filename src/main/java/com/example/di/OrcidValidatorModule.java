package com.example.di;

import com.example.service.OrcidValidator;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class OrcidValidatorModule {
    @Provides
    @Singleton
    OrcidValidator provideOrcidValidator() {
        return new OrcidValidator();
    }
}
