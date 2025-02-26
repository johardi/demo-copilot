package com.example.api;

import com.example.service.OrcidValidator;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class OrcidResource_Factory implements Factory<OrcidResource> {
  private final Provider<OrcidValidator> validatorProvider;

  public OrcidResource_Factory(Provider<OrcidValidator> validatorProvider) {
    this.validatorProvider = validatorProvider;
  }

  @Override
  public OrcidResource get() {
    return newInstance(validatorProvider.get());
  }

  public static OrcidResource_Factory create(Provider<OrcidValidator> validatorProvider) {
    return new OrcidResource_Factory(validatorProvider);
  }

  public static OrcidResource newInstance(OrcidValidator validator) {
    return new OrcidResource(validator);
  }
}
