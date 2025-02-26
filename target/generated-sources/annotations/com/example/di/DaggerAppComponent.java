package com.example.di;

import com.example.api.OrcidResource;
import com.example.service.OrcidValidator;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class DaggerAppComponent {
  private DaggerAppComponent() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static AppComponent create() {
    return new Builder().build();
  }

  public static final class Builder {
    private OrcidModule orcidModule;

    private Builder() {
    }

    public Builder orcidModule(OrcidModule orcidModule) {
      this.orcidModule = Preconditions.checkNotNull(orcidModule);
      return this;
    }

    public AppComponent build() {
      if (orcidModule == null) {
        this.orcidModule = new OrcidModule();
      }
      return new AppComponentImpl(orcidModule);
    }
  }

  private static final class AppComponentImpl implements AppComponent {
    private final AppComponentImpl appComponentImpl = this;

    private Provider<OrcidValidator> provideOrcidValidatorProvider;

    private AppComponentImpl(OrcidModule orcidModuleParam) {

      initialize(orcidModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final OrcidModule orcidModuleParam) {
      this.provideOrcidValidatorProvider = DoubleCheck.provider(OrcidModule_ProvideOrcidValidatorFactory.create(orcidModuleParam));
    }

    @Override
    public OrcidResource getOrcidResource() {
      return new OrcidResource(provideOrcidValidatorProvider.get());
    }
  }
}
