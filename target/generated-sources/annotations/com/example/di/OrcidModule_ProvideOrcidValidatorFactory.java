package com.example.di;

import com.example.service.OrcidValidator;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class OrcidModule_ProvideOrcidValidatorFactory implements Factory<OrcidValidator> {
  private final OrcidModule module;

  public OrcidModule_ProvideOrcidValidatorFactory(OrcidModule module) {
    this.module = module;
  }

  @Override
  public OrcidValidator get() {
    return provideOrcidValidator(module);
  }

  public static OrcidModule_ProvideOrcidValidatorFactory create(OrcidModule module) {
    return new OrcidModule_ProvideOrcidValidatorFactory(module);
  }

  public static OrcidValidator provideOrcidValidator(OrcidModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideOrcidValidator());
  }
}
