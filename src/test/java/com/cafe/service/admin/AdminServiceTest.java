package com.cafe.service.admin;

import com.cafe.service.admin.impl.AdminCreator;
import com.cafe.service.admin.impl.AdminValidator;
import com.cafe.service.admin.impl.SensitiveDataEncryptor;
import com.cafe.service.admin.vo.EncryptedSignUpForm;
import com.cafe.service.admin.vo.EncryptedSignUpFormFixture;
import com.cafe.service.admin.vo.SignUpForm;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.cafe.service.admin.vo.SignUpFormFixture.STANDARD;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AdminServiceTest {

    @InjectMocks
    AdminService adminService;
    @Mock
    SensitiveDataEncryptor sensitiveDataEncryptor;
    @Mock
    AdminValidator adminValidator;
    @Mock
    AdminCreator adminCreator;

    @Test
    void 회원가입을_할_수_있다() {
        // given
        SignUpForm signUpForm = STANDARD.newInstance();
        EncryptedSignUpForm encryptedSignUpForm = EncryptedSignUpFormFixture.STANDARD.newInstance();

        given(sensitiveDataEncryptor.encrypt(signUpForm)).willReturn(encryptedSignUpForm);

        // when
        adminService.signUp(signUpForm);

        // then
        then(adminValidator).should(times(1)).validate(signUpForm);
        then(sensitiveDataEncryptor).should(times(1)).encrypt(signUpForm);
        then(adminCreator).should(times(1)).create(encryptedSignUpForm);
    }

}
