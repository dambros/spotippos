package br.com.vivareal.spotippos.config;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import br.com.vivareal.spotippos.BaseTest;
import br.com.vivareal.spotippos.model.Province;
import br.com.vivareal.spotippos.repository.ProvinceRepository;

import static org.hamcrest.Matchers.equalTo;

public class DataLoaderTest extends BaseTest {

  @Autowired
  private ProvinceRepository provinceRepository;

  @Before
  public void init() {
    super.init();
  }

  @Test
  public void testProvinceLoader() {
    //make sure there are 6 provinces loaded after the Spring context has been setup

    List<Province> provinces = (List<Province>) provinceRepository.findAll();
    MatcherAssert.assertThat(provinces.size(), equalTo(6));
    MatcherAssert.assertThat(containsName(provinces, "Gode"), equalTo(Boolean.TRUE));
    MatcherAssert.assertThat(containsName(provinces, "Ruja"), equalTo(Boolean.TRUE));
    MatcherAssert.assertThat(containsName(provinces, "Jaby"), equalTo(Boolean.TRUE));
    MatcherAssert.assertThat(containsName(provinces, "Scavy"), equalTo(Boolean.TRUE));
    MatcherAssert.assertThat(containsName(provinces, "Groola"), equalTo(Boolean.TRUE));
    MatcherAssert.assertThat(containsName(provinces, "Nova"), equalTo(Boolean.TRUE));
  }


  private static boolean containsName(List<Province> list, String name) {
    for (Province obj : list) {
      if (obj.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }

}
