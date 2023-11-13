/*
 * © ООО «Наука», 2023 . Все права сохранены за правообладателем.
 * Правообладателем настоящей программы ООО «Наука» (Российская Федерация, г.
 * Санкт-Петербург, ИНН 7804056944). Программа является проприетарной и
 * распространяется на условиях, определенных лицензионным соглашением с
 * правообладателем.
 *
 * © “NAUKA”, LLC, 2023. All rights reserved.  The rightholder of the present
 * program is  “NAUKA”, LLC (Russia, St. Petersburg, tax identification number
 * 7804056944). The program is proprietary and is distributed under the terms of
 * the license agreement with the rightholder.
 */

package ru.dlts.record.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration()
        .setPreferNestedProperties(false)
        .setMatchingStrategy(MatchingStrategies.STRICT);
    return modelMapper;
  }
}
