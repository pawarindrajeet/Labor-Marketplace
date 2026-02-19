// Language Translations - English & Marathi
const translations = {
  en: {
    // Navbar
    navbar_title: "Labor Marketplace",
    navbar_login: "Login",
    navbar_register: "Register",
    navbar_logout: "Logout",
    
    // Hero Section
    hero_heading: "Find Work or Hire Workers",
    hero_subtitle: "Connect farmers with skilled workers. Post jobs, find opportunities, and build your network.",
    
    // Statistics
    stat_active_jobs: "Active Jobs",
    stat_available_workers: "Available Workers",
    stat_towns: "Towns",
    stat_trusted: "Trusted",
    
    // Filters
    filter_town: "Filter by Town",
    filter_all_towns: "All Towns",
    filter_gender: "Filter by Gender",
    filter_all_genders: "All Genders",
    filter_male: "Male",
    filter_female: "Female",
    sort_by: "Sort By",
    sort_newest: "Date (Newest First)",
    sort_wage: "Wage (High to Low)",
    apply_filters: "Apply Filters",
    
    // Sections
    browse_opportunities: "Browse Opportunities",
    all_posts: "All Posts",
    farmer_posts: "Farmer Posts",
    worker_posts: "Worker Posts",
    post_availability: "Post Availability",
    post_job: "Post Job",
    post_or_availability: "Post Job / Availability",
    
    // Card Labels
    farmer_name: "Farmer Name",
    worker_name: "Worker Name",
    contact: "Contact",
    wage: "Wage",
    expected_wage: "Expected Wage",
    workers_needed: "Workers needed",
    skills: "Skills",
    preferred_gender: "Preferred Gender",
    from: "From",
    to: "To",
    town: "Town",
    availability: "Availability",
    interests_received: "Interests Received",
    farmer_post: "Farmer Post",
    worker_post: "Worker Post",
    
    // Buttons
    interested: "Interested",
    yes_interested: "Yes, Interested",
    mark_full: "Mark as Full",
    delete: "Delete",
    post_new_job: "Post New Job",
    
    // Messages
    logged_in_worker: "You are logged in as Worker",
    logged_in_farmer: "You are logged in as Farmer",
    
    // Dashboard Titles
    worker_dashboard: "Worker Dashboard",
    farmer_dashboard: "Farmer Dashboard",
    available_jobs: "Available Jobs",
    all_job_posts: "All Job Posts",
    all_available_workers: "All Available Workers"
  },
  
  mr: {
    // Navbar
    navbar_title: "मजूर बाजार",
    navbar_login: "लॉगिन",
    navbar_register: "नोंदणी",
    navbar_logout: "लॉगआउट",
    
    // Hero Section
    hero_heading: "काम शोधा किंवा कामगार भाड़े पर लें",
    hero_subtitle: "शेतकरी आणि कुशल कामगारांना जोडा। नोकऱ्या पोस्ट करा, संधी शोधा आणि आपले नेटवर्क तयार करा।",
    
    // Statistics
    stat_active_jobs: "सक्रिय नोकऱ्या",
    stat_available_workers: "उपलब्ध कामगार",
    stat_towns: "शहरे",
    stat_trusted: "विश्वसनीय",
    
    // Filters
    filter_town: "शहराने फिल्टर करा",
    filter_all_towns: "सर्व शहरे",
    filter_gender: "लिंगाने फिल्टर करा",
    filter_all_genders: "सर्व लिंग",
    filter_male: "पुरुष",
    filter_female: "महिला",
    sort_by: "यानुसार क्रमवारी करा",
    sort_newest: "तारीख (नवीनतम प्रथम)",
    sort_wage: "मजुरी (उच्च ते निम्न)",
    apply_filters: "फिल्टर लागू करा",
    
    // Sections
    browse_opportunities: "संधी वर्णन करा",
    all_posts: "सर्व पोस्ट",
    farmer_posts: "शेतकरी पोस्ट",
    worker_posts: "कामगार पोस्ट",
    post_availability: "उपलब्धता पोस्ट करा",
    post_job: "नोकरी पोस्ट करा",
    post_or_availability: "पोस्ट नोकरी / उपलब्धता",
    
    // Card Labels
    farmer_name: "शेतकरीचे नाव",
    worker_name: "कामगारचे नाव",
    contact: "संपर्क",
    wage: "मजुरी",
    expected_wage: "अपेक्षित मजुरी",
    workers_needed: "आवश्यक कामगार",
    skills: "कौशल्य",
    preferred_gender: "पसंदीदा लिंग",
    from: "पासून",
    to: "हून",
    town: "शहर",
    availability: "उपलब्धता",
    interests_received: "मिळालेली आग्रह",
    farmer_post: "शेतकरी पोस्ट",
    worker_post: "कामगार पोस्ट",
    
    // Buttons
    interested: "आग्रह",
    yes_interested: "हो, आग्रह आहे",
    mark_full: "पूर्ण म्हणून चिन्हांकित करा",
    delete: "हटवा",
    post_new_job: "नई नोकरी पोस्ट करा",
    
    // Messages
    logged_in_worker: "आप कामगार म्हणून लॉगिन केले आहात",
    logged_in_farmer: "आप शेतकरी म्हणून लॉगिन केले आहात",
    
    // Dashboard Titles
    worker_dashboard: "कामगार डॅशबोर्ड",
    farmer_dashboard: "शेतकरी डॅशबोर्ड",
    available_jobs: "उपलब्ध नोकऱ्या",
    all_job_posts: "सर्व नोकरी पोस्ट",
    all_available_workers: "सर्व उपलब्ध कामगार"
  }
};

// Town names mapping (English to Marathi)
const townTranslations = {
  "Agar": "अगर",
  "Ankali": "अंकली",
  "Arag": "अराग",
  "Balwad": "बलवाड",
  "Bamnoli": "बामनोली",
  "Bedag": "बेडाग",
  "Belanki": "बेलंकी",
  "Bhose": "भोसे",
  "Bijageri": "बिजागेरी",
  "Brahmanpuri": "ब्राह्मणपुरी",
  "Budhavgaon": "बुधावगाव",
  "Chinchani": "चिंचणी",
  "Dhavali": "धावली",
  "Dudhani": "दुधणी",
  "Ganeshpur": "गणेशपुर",
  "Garlawad": "गारलवाड",
  "Gudhe": "गुढे",
  "Gulvanchi": "गुलवंची",
  "Harnal": "हर्नाल",
  "Ingalgi": "इंगलगी",
  "Jambli": "जांबली",
  "Kadiyanal": "कडियनाल",
  "Kavthepiran": "कव्थेपिरान",
  "Khanderajuri": "खंडेरजुरी",
  "Kukudwad": "कुकुडवाड",
  "Kupwad (CT)": "कुपवाड",
  "Laxmiwadi": "लक्ष्मीवाडी",
  "Madihal": "मदिहाल",
  "Malgaon": "मालगाव",
  "Maliwadi": "मालीवाडी",
  "Mangrul": "मंगरुल",
  "Miraj (M Cl)": "मिराज",
  "Mhaisal": "म्हैसाल",
  "Narwad": "नारवाड",
  "Nandre": "नांद्रे",
  "Nandani": "नंदणी",
  "Nimshirgaon": "निमशिरगाव",
  "Padali": "पडाली",
  "Patgaon": "पतगाव",
  "Rupbhavani": "रुपभवानी",
  "Sagara": "सागरा",
  "Sangli (M Corp)": "सांगली",
  "Sangliwadi (CT)": "सांगलीवाडी",
  "Soni": "सोनी",
  "Tupari": "तुपारी",
  "Wanlesswadi (CT)": "वानलेसवाडी",
  "Yesugade": "येसुगडे",
  "Alate": "अलाते",
  "Amaljheri": "अमालझेरी",
  "Anjani": "अंजनी",
  "Balawadi": "बालावाडी",
  "Bhilawadi": "भिलावाडी",
  "Borgaon": "बोरगाव",
  "Choudeshwar": "चौडेश्वर",
  "Dahiwadi": "दहीवाडी",
  "Dhamani": "धमानी",
  "Dhawali": "धावली",
  "Dhavalipur": "धावलीपुर",
  "Dongarsoni": "डोंगरसोनी",
  "Ghanand": "घनंद",
  "Ghatnandre": "घाटनांद्रे"
};

// Get current language from localStorage, default to English
function getCurrentLanguage() {
  return localStorage.getItem('language') || 'en';
}

// Set language in localStorage
function setLanguage(lang) {
  localStorage.setItem('language', lang);
  location.reload(); // Reload page to apply language
}

// Get translated text
function t(key) {
  const lang = getCurrentLanguage();
  
  // Check if it's a town name
  if (townTranslations[key] && lang === 'mr') {
    return townTranslations[key];
  }
  
  // Check translations object
  if (translations?.[lang]?.[key]) {
    return translations[lang][key];
  }
  
  // Fallback to English
  if (translations?.['en']?.[key]) {
    return translations['en'][key];
  }
  
  // Return key if no translation found
  return key;
}

// Initialize language dropdown on page load
document.addEventListener('DOMContentLoaded', function() {
  const langSelector = document.getElementById('langSelector');
  if (langSelector) {
    langSelector.value = getCurrentLanguage();
    langSelector.addEventListener('change', function() {
      setLanguage(this.value);
    });
  }
  applyLanguageToPage();
});

// Apply translations to all elements with data-i18n attribute
function applyLanguageToPage() {
  document.querySelectorAll('[data-i18n]').forEach(element => {
    const key = element.dataset.i18n;
    const translated = t(key);
    if (translated !== key || element.textContent.trim() === '') {
      element.textContent = translated;
    }
  });
  
  // Apply to placeholders
  document.querySelectorAll('[data-i18n-placeholder]').forEach(element => {
    const key = element.dataset.i18nPlaceholder;
    element.placeholder = t(key);
  });
  
  // Apply to titles/aria-labels
  document.querySelectorAll('[data-i18n-title]').forEach(element => {
    const key = element.dataset.i18nTitle;
    element.title = t(key);
  });

  // Translate town names
  translateTownNames();
}

// Function to translate town names in dropdowns
function translateTownNames() {
  const lang = getCurrentLanguage();
  
  // Find all select elements that contain town options
  document.querySelectorAll('select').forEach(select => {
    Array.from(select.options).forEach(option => {
      if (option.value !== '' && option.value !== 'All Towns') {
        const englishName = option.textContent.trim();
        const marathiName = townTranslations[englishName];
        
        if (marathiName && lang === 'mr') {
          option.textContent = marathiName;
        } else if (lang === 'en') {
          option.textContent = englishName;
        }
      }
    });
  });
}
