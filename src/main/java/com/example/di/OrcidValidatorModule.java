package com.example.di;

import com.example.service.OrcidFormatValidator;
import com.example.service.OrcidApiService;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class OrcidValidatorModule {
    @Provides
    @Singleton
    OrcidFormatValidator provideOrcidFormatValidator() {
        return new OrcidFormatValidator();
    }

    @Provides
    @Singleton
    OrcidApiService provideOrcidApiService(OrcidFormatValidator validator) {
        return new OrcidApiService(validator);
    }
}
