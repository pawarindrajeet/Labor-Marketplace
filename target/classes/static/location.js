// --- src/main/resources/static/location.js ---

const locationData = {
  "Maharashtra": {
    "Sangli": [
      "Agar", "Ankali", "Arag", "Balwad", "Bamnoli", "Bedag", "Belanki", "Bhose", 
      "Bijageri", "Brahmanpuri", "Budhavgaon", "Chinchani", "Dhavali", "Dudhani", 
      "Ganeshpur", "Garlawad", "Gudhe", "Gulvanchi", "Harnal", "Ingalgi", "Jambli", 
      "Kadiyanal", "Kavthepiran", "Khanderajuri", "Kukudwad", "Kupwad (CT)", "Laxmiwadi", 
      "Madihal", "Malgaon", "Maliwadi", "Mangrul", "Miraj (M Cl)", "Mhaisal", "Narwad", 
      "Nandre", "Nandani", "Nimshirgaon", "Padali", "Patgaon", "Rupbhavani", "Sagara", 
      "Sangli (M Corp)", "Sangliwadi (CT)", "Soni", "Tupari", "Wanlesswadi (CT)", "Yesugade"
    ],
    "Kolhapur": ["Alate", "Anjani", "Bhilawadi"],
    "Satara": [
      "Dahiwadi", "Borgaon", "Amaljheri", "Balawadi", "Choudeshwar", "Dhamani", 
      "Dhawali", "Dhavalipur", "Dongarsoni", "Ghanand", "Ghatnandre"
    ]
  },
  "Karnataka": {
    "Belagavi": [] 
  }
};

document.addEventListener("DOMContentLoaded", function() {
  const stateSelect = document.getElementById("stateSelector");
  if (stateSelect) {
    for (let state in locationData) {
      let option = document.createElement("option");
      option.value = state;
      option.text = state;
      stateSelect.add(option);
    }
  }
});

function updateDistricts() {
  const stateSelect = document.getElementById("stateSelector");
  const districtSelect = document.getElementById("districtSelector");
  const townSelect = document.getElementById("townSelector");
  
  districtSelect.innerHTML = '<option value="">2. Select District</option>';
  townSelect.value = "";
  townSelect.disabled = true;

  const selectedState = stateSelect.value;
  if (selectedState !== "") {
    districtSelect.disabled = false;
    const districts = locationData[selectedState];
    for (let district in districts) {
      let option = document.createElement("option");
      option.value = district;
      option.text = district;
      districtSelect.add(option);
    }
  } else {
    districtSelect.disabled = true;
  }
  
  // If this is used in a filter, trigger the filter update automatically
  if (typeof applyFilters === 'function') applyFilters();
}

function updateTowns() {
  const stateSelect = document.getElementById("stateSelector");
  const districtSelect = document.getElementById("districtSelector");
  const townSelect = document.getElementById("townSelector");
  const allBackendTowns = document.querySelectorAll(".backend-town-option");
  
  const selectedState = stateSelect.value;
  const selectedDistrict = districtSelect.value;
  townSelect.value = ""; 

  if (selectedDistrict !== "") {
    townSelect.disabled = false;
    const validVillages = locationData[selectedState][selectedDistrict];
    
    allBackendTowns.forEach(option => {
      // Ignore the "All Towns" or "Select Town" placeholder
      if(option.value === "") return; 
      
      const villageName = option.getAttribute("data-name") || option.text;
      if (validVillages.includes(villageName)) {
        option.style.display = ""; 
      } else {
        option.style.display = "none";  
      }
    });
  } else {
    townSelect.disabled = true;
    allBackendTowns.forEach(opt => {
      if(opt.value !== "") opt.style.display = "none";
    });
  }
  
  // If this is used in a filter, trigger the filter update automatically
  if (typeof applyFilters === 'function') applyFilters();
}