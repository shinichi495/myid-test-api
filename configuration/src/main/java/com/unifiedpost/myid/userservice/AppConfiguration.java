package com.unifiedpost.myid.userservice;

import com.unifiedpost.myid.userservice.db.UserAdapterImpl;
import com.unifiedpost.myid.userservice.http.SignicatAdapterImpl;
import com.unifiedpost.myid.userservice.usecases.*;
import com.unifiedpost.myid.userservice.usecases.adapter.SignicatAdapter;
import com.unifiedpost.myid.userservice.usecases.adapter.UserAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

  @Bean
  public CreateDossierUseCase createDossierUseCase(SignicatAdapter signicatAdapter) {
    return new CreateDossierUseCase(signicatAdapter);
  }

  @Bean
  public CreateProcessUseCase createProcessUseCase(SignicatAdapter signicatAdapter) {
    return new CreateProcessUseCase(signicatAdapter);
  }

  @Bean
  public SignicatAdapter signicatAdapter() {
    return new SignicatAdapterImpl();
  }

  @Bean
  public GetProcessUseCase getProcessUseCase(SignicatAdapter signicatAdapter) {
    return new GetProcessUseCase(signicatAdapter);
  }

  @Bean
  public StartVerificationUseCase startVerificationUseCase(SignicatAdapter signicatAdapter) {
    return new StartVerificationUseCase(signicatAdapter);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public UserAdapter userAdapter() {
    return new UserAdapterImpl();
  }

}
