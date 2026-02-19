package com.labor;

import com.labor.model.Town;
import com.labor.repository.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private TownRepository townRepository;

    @Override
    public void run(String... args) throws Exception {
        if (townRepository.count() == 0) {
            townRepository.save(new Town("Agar"));
            townRepository.save(new Town("Ankali"));
            townRepository.save(new Town("Arag"));
            townRepository.save(new Town("Balwad"));
            townRepository.save(new Town("Bamnoli"));
            townRepository.save(new Town("Bedag"));
            townRepository.save(new Town("Belanki"));
            townRepository.save(new Town("Bhose"));
            townRepository.save(new Town("Bijageri"));
            townRepository.save(new Town("Brahmanpuri"));
            townRepository.save(new Town("Budhavgaon"));
            townRepository.save(new Town("Chinchani"));
            townRepository.save(new Town("Dhavali"));
            townRepository.save(new Town("Dudhani"));
            townRepository.save(new Town("Ganeshpur"));
            townRepository.save(new Town("Garlawad"));
            townRepository.save(new Town("Gudhe"));
            townRepository.save(new Town("Gulvanchi"));
            townRepository.save(new Town("Harnal"));
            townRepository.save(new Town("Ingalgi"));
            townRepository.save(new Town("Jambli"));
            townRepository.save(new Town("Kadiyanal"));
            townRepository.save(new Town("Kavthepiran"));
            townRepository.save(new Town("Khanderajuri"));
            townRepository.save(new Town("Kukudwad"));
            townRepository.save(new Town("Kupwad (CT)"));
            townRepository.save(new Town("Laxmiwadi"));
            townRepository.save(new Town("Madihal"));
            townRepository.save(new Town("Malgaon"));
            townRepository.save(new Town("Maliwadi"));
            townRepository.save(new Town("Mangrul"));
            townRepository.save(new Town("Miraj (M Cl)"));
            townRepository.save(new Town("Mhaisal"));
            townRepository.save(new Town("Narwad"));
            townRepository.save(new Town("Nandre"));
            townRepository.save(new Town("Nandani"));
            townRepository.save(new Town("Nimshirgaon"));
            townRepository.save(new Town("Padali"));
            townRepository.save(new Town("Patgaon"));
            townRepository.save(new Town("Rupbhavani"));
            townRepository.save(new Town("Sagara"));
            townRepository.save(new Town("Sangli (M Corp)"));
            townRepository.save(new Town("Sangliwadi (CT)"));
            townRepository.save(new Town("Soni"));
            townRepository.save(new Town("Tupari"));
            townRepository.save(new Town("Wanlesswadi (CT)"));
            townRepository.save(new Town("Yesugade"));
            townRepository.save(new Town("Alate"));
            townRepository.save(new Town("Amaljheri"));
            townRepository.save(new Town("Anjani"));
            townRepository.save(new Town("Balawadi"));
            townRepository.save(new Town("Bhilawadi"));
            townRepository.save(new Town("Borgaon"));
            townRepository.save(new Town("Chinchani"));
            townRepository.save(new Town("Choudeshwar"));
            townRepository.save(new Town("Dahiwadi"));
            townRepository.save(new Town("Dhamani"));
            townRepository.save(new Town("Dhawali"));
            townRepository.save(new Town("Dhavalipur"));
            townRepository.save(new Town("Dongarsoni"));
            townRepository.save(new Town("Ganeshpur"));
            townRepository.save(new Town("Ghanand"));
            townRepository.save(new Town("Ghatnandre"));
            townRepository.save(new Town("Gheradi"));
            townRepository.save(new Town("Gorewadi"));
            townRepository.save(new Town("Hingangaon"));
            townRepository.save(new Town("Jamb"));
            townRepository.save(new Town("Kakatwadi"));
            townRepository.save(new Town("Kamalapur"));
            townRepository.save(new Town("Khanderajuri"));
            townRepository.save(new Town("Khaproli"));
            townRepository.save(new Town("Kherade"));
            townRepository.save(new Town("Khatav"));
            townRepository.save(new Town("Kumbhari"));
            townRepository.save(new Town("Kuralap"));
            townRepository.save(new Town("Lomwadi"));
            townRepository.save(new Town("Mahim"));
            townRepository.save(new Town("Manjarde"));
            townRepository.save(new Town("Matwade"));
            townRepository.save(new Town("Mhaisalkarwadi"));
            townRepository.save(new Town("Nagaon"));
            townRepository.save(new Town("Nandgaon"));
            townRepository.save(new Town("Nimani"));
            townRepository.save(new Town("Paluskarwadi"));
            townRepository.save(new Town("Pangari"));
            townRepository.save(new Town("Pedh"));
            townRepository.save(new Town("Ranjani"));
            townRepository.save(new Town("Savalaj"));
            townRepository.save(new Town("Siddhewadi"));
            townRepository.save(new Town("Soni"));
            townRepository.save(new Town("Tadawale"));
            townRepository.save(new Town("Tasgaon (M Cl)"));
            townRepository.save(new Town("Vajarwade"));
            townRepository.save(new Town("Vasumbhe"));
            townRepository.save(new Town("Yelavi"));
        }
    }
}
