package io.jenkins.plugins.fontawesome.api;

import org.junit.jupiter.api.Test;

import io.jenkins.plugins.fontawesome.api.SvgTag.FontAwesomeStyle;
import io.jenkins.plugins.util.JenkinsFacade;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests the class {@link SvgTag}.
 *
 * @author Ullrich Hafner
 */
class SvgTagCreatorTest {
    @Test
    void shouldCreateIcon() {
        JenkinsFacade jenkins = mock(JenkinsFacade.class);
        when(jenkins.getImagePath("/plugin/font-awesome-api/sprites/solid.svg#hello")).thenReturn("path-solid");

        assertThat(new SvgTag("hello", jenkins).render())
                .isEqualTo("<svg class=\"fa-svg-icon\"><use href=\"path-solid\"></use></svg>");

        when(jenkins.getImagePath("/plugin/font-awesome-api/sprites/regular.svg#hello")).thenReturn("path-regular");
        SvgTag tag = new SvgTag("hello", jenkins, FontAwesomeStyle.REGULAR);
        assertThat(tag.render())
                .isEqualTo("<svg class=\"fa-svg-icon\"><use href=\"path-regular\"></use></svg>");

        assertThat(tag.withClasses("icon-md").render())
                .isEqualTo("<svg class=\"icon-md svg-icon\"><use href=\"path-regular\"></use></svg>");
    }
}
