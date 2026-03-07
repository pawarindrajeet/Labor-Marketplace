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
            
            // Maharashtra - Sangli District
            townRepository.save(new Town("Agar", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Ankali", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Arag", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Balwad", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Bamnoli", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Bedag", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Belanki", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Bhose", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Bijageri", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Brahmanpuri", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Budhavgaon", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Chinchani", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Dhavali", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Dudhani", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Ganeshpur", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Garlawad", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Gudhe", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Gulvanchi", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Harnal", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Ingalgi", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Jambli", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Kadiyanal", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Kavthepiran", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Khanderajuri", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Kukudwad", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Kupwad (CT)", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Laxmiwadi", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Madihal", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Malgaon", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Maliwadi", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Mangrul", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Miraj (M Cl)", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Mhaisal", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Narwad", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Nandre", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Nandani", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Nimshirgaon", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Padali", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Patgaon", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Rupbhavani", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Sagara", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Sangli (M Corp)", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Sangliwadi (CT)", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Soni", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Tupari", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Wanlesswadi (CT)", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Yesugade", "Sangli", "Maharashtra"));

            // Maharashtra - Kolhapur District
            townRepository.save(new Town("Alate", "Kolhapur", "Maharashtra"));
            townRepository.save(new Town("Anjani", "Kolhapur", "Maharashtra"));
            townRepository.save(new Town("Bhilawadi", "Kolhapur", "Maharashtra"));

            // Maharashtra - Satara District
            townRepository.save(new Town("Amaljheri", "Satara", "Maharashtra"));
            townRepository.save(new Town("Balawadi", "Satara", "Maharashtra"));
            townRepository.save(new Town("Borgaon", "Satara", "Maharashtra"));
            townRepository.save(new Town("Choudeshwar", "Satara", "Maharashtra"));
            townRepository.save(new Town("Dahiwadi", "Satara", "Maharashtra"));
            townRepository.save(new Town("Dhamani", "Satara", "Maharashtra"));
            townRepository.save(new Town("Dhawali", "Satara", "Maharashtra"));
            townRepository.save(new Town("Dhavalipur", "Satara", "Maharashtra"));
            townRepository.save(new Town("Dongarsoni", "Satara", "Maharashtra"));
            townRepository.save(new Town("Ghanand", "Satara", "Maharashtra"));
            townRepository.save(new Town("Ghatnandre", "Satara", "Maharashtra"));
            
            // Other Maharashtra Villages (Assigned to Sangli for consistency, or adjust as needed)
            townRepository.save(new Town("Gheradi", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Gorewadi", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Hingangaon", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Jamb", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Kakatwadi", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Kamalapur", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Khaproli", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Kherade", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Khatav", "Satara", "Maharashtra"));
            townRepository.save(new Town("Kumbhari", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Kuralap", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Lomwadi", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Mahim", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Manjarde", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Matwade", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Mhaisalkarwadi", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Nagaon", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Nandgaon", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Nimani", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Paluskarwadi", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Pangari", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Pedh", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Ranjani", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Savalaj", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Siddhewadi", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Tadawale", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Tasgaon (M Cl)", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Vajarwade", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Vasumbhe", "Sangli", "Maharashtra"));
            townRepository.save(new Town("Yelavi", "Sangli", "Maharashtra"));
        }
    }
}