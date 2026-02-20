// Language Translations - English & Marathi
const translations = {
  en: {
    // Login Page
    login_title: "Login to Shetkari-Kamgar",
    mobile_number: "Mobile Number",
    password: "Password",
    remember_me: "Remember Me",
    dont_have_account: "Don't have an account? Register",
    reg_success: "Registration successful! Please log in.",
    invalid_credentials: "Invalid mobile number or password.",

    // Registration Page
    register_title: "Register for Shetkari-Kamgar",
    full_name: "Full Name",
    role: "Role",
    select_role: "Select Role",
    farmer: "Farmer",
    worker_helper: "Worker (Helper)",
    gender: "Gender",
    select_gender: "Select Gender",
    male: "Male",
    female: "Female",
    select_town: "Select Town",
    back_to_home: "Back to Home",

    // Navbar
    navbar_title: "Shetkari-Kamgar",
    navbar_login: "Login",
    navbar_register: "Register",
    navbar_logout: "Logout",
    
    // Hero Section
    hero_heading: "Welcome to Shetkari-Kamgar",
    hero_subtitle: "The trusted local platform connecting farmers with skilled laborers. Find work, hire help, and grow together.",    
    
    // Statistics
    stat_active_jobs: "Active Jobs",
    stat_available_workers: "Available Workers",
    stat_towns: "Towns",
    stat_trusted: "Trusted",
    
    // Filters
    filter_town: "Town",
    filter_all_towns: "All Towns",
    filter_gender: "Gender",
    filter_all_genders: "All Genders",
    filter_male: "Male",
    filter_female: "Female",
    sort_by: "Sort By",
    sort_newest: "Newest First",
    sort_wage: "Wage (High to Low)",
    apply_filters: "Apply Filters",
    
    // Sections
    browse_opportunities: "Browse Opportunities",
    all_posts: "Dashboard Overview",
    farmer_posts: "My Job History",
    worker_posts: "Find Workers",
    post_availability: "Post Availability",
    post_job: "Post Job",
    post_or_availability: "Post Job / Availability",
    
    // Card Labels & General UI
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
    all_available_workers: "All Available Workers",

    // NEW DASHBOARD & FORM ADDITIONS
    need_help_farm: "Need help on the farm?",
    post_job_desc: "Post a job now to find trusted local workers quickly.",
    post_new_job_btn: "➕ Post a New Job",
    job_history_title: "My Posted Jobs History",
    job_details: "1. Job Details",
    job_title: "Job Title",
    wage_per_day: "Wage per Day (₹)",
    complaint_submitted_success: "Your complaint has been submitted securely to the Admin for review.",
    report_issue: "Report Issue",
    my_applications: "My Applications",
    available_workers: "Available Workers",
    no_workers_found: "No workers found",
    no_workers_desc: "There are currently no available workers matching your criteria.",
    skills_label: "Skills",
    availability_label: "Availability",
    contact_label: "Contact",
    im_interested_btn: "I'm Interested",
    other_local_jobs: "Other Local Jobs",
    wage_label: "Wage",
    needed_label: "Needed",
    my_posted_jobs_history: "My Posted Jobs History",
    no_jobs_posted: "No jobs posted yet",
    no_jobs_desc: "When you post a job, your history will appear here.",
    status_completed: "Completed",
    status_open: "Open",
    posted_label: "Posted",
    responses_label: "Responses",
    mark_as_full_btn: "Mark as Full",
    delete_post_btn: "Delete Post",
    post_new_job_req: "Post New Job Requirement",
    step_1_job_details: "1. Job Details",
    job_title_label: "Job Title",
    wage_per_day_label: "Wage per Day (₹)",
    job_desc_label: "Job Description",
    step_2_worker_req: "2. Worker Requirements",
    number_needed_label: "Number Needed",
    preferred_gender_label: "Preferred Gender",
    gender_any: "Any",
    skills_required_label: "Skills Required",
    step_3_location: "3. Location & Timing",
    farm_location_label: "Farm Location",
    start_date_label: "Start Date & Time",
    end_date_label: "End Date & Time",
    post_job_submit_btn: "Post Job",
    select_state: "1. Select State",
    first_select_state: "2. First Select State",
    select_village: "3. Select Village / Town",

    report_issue_title: "Report an Issue",
    report_issue_desc: "If you faced an issue with another user (e.g., non-payment, fake profile, harassment), please report it here. Our Admin will investigate immediately.",
    issue_type_label: "Issue Type",
    select_category: "Select a category...",
    payment_issue: "Payment Issue",
    fake_profile: "Fake Profile / Scam",
    bad_behavior: "Bad Behavior / Harassment",
    other: "Other",
    details_label: "Details",
    details_placeholder: "Please provide the name or mobile number of the user, and describe exactly what happened...",
    cancel_btn: "Cancel",
    submit_complaint_btn: "Submit Complaint",

    ready_to_work: "Ready to work?",
    post_avail_desc: "Post your availability so farmers in your town can contact you directly.",
    post_avail_btn: "➕ Post Availability",
    available_farm_jobs: "Available Farm Jobs",
    no_jobs_found: "No jobs found",
    no_jobs_found_desc: "There are currently no open jobs matching your criteria.",
    other_worker_posts: "Other Worker Posts",
    worker_post_badge: "Worker Post",
    my_job_applications: "My Job Applications",
    no_applications_yet: "No applications yet",
    no_applications_desc: "When you apply for a job, it will appear here so you can track it.",
    job_closed: "Job Closed",
    active: "Active",
    applied_on: "Applied on",
    farmer_contact: "Farmer Contact",
    post_availability_title: "Post Your Availability",
    availability_details_label: "Availability Details",
    expected_wage_label: "Expected Wage per Day (₹)",
    work_location_label: "Work Location",

    skills_placeholder: "e.g., Farming, Ploughing, Harvesting",
    availability_placeholder: "e.g., Available from Monday to Friday, 8 AM to 6 PM",
    wage_placeholder: "e.g., 500",
    example_start_date: "Example: 3 Mar 2026 9:00 AM",
    example_end_date: "Example: 4 Mar 2026 5:00 PM"
  },
  
  mr: {
    // Login Page
    login_title: "शेतकरी-कामगार मध्ये लॉगिन करा",
    mobile_number: "मोबाईल नंबर",
    password: "पासवर्ड",
    remember_me: "माझी नोंद ठेवा",
    dont_have_account: "खाते नाही? नोंदणी करा",
    reg_success: "नोंदणी यशस्वी! कृपया लॉगिन करा.",
    invalid_credentials: "अवैध मोबाईल नंबर किंवा पासवर्ड.",

    // Registration Page
    register_title: "शेतकरी-कामगार साठी नोंदणी करा",
    full_name: "पूर्ण नाव",
    role: "भूमिका (Role)",
    select_role: "भूमिका निवडा",
    farmer: "शेतकरी",
    worker_helper: "कामगार (मदतनीस)",
    gender: "लिंग",
    select_gender: "लिंग निवडा",
    male: "पुरुष",
    female: "महिला",
    select_town: "शहर निवडा",
    back_to_home: "मुख्यपृष्ठावर परत जा",

    // Navbar
    navbar_title: "शेतकरी-कामगार",
    navbar_login: "लॉगिन",
    navbar_register: "नोंदणी",
    navbar_logout: "लॉगआउट",
    
    // Hero Section
    hero_heading: "शेतकरी-कामगार मध्ये आपले स्वागत आहे",
    hero_subtitle: "शेतकरी आणि कुशल मजुरांना जोडणारे विश्वासार्ह स्थानिक व्यासपीठ. काम शोधा, मदत मिळवा आणि एकत्र प्रगती करा.",
    
    // Statistics
    stat_active_jobs: "सक्रिय नोकऱ्या",
    stat_available_workers: "उपलब्ध कामगार",
    stat_towns: "शहरे",
    stat_trusted: "विश्वसनीय",
    
    // Filters
    filter_town: "गाव/शहर",
    filter_all_towns: "सर्व गावे",
    filter_gender: "लिंग",
    filter_all_genders: "सर्व लिंग",
    filter_male: "पुरुष",
    filter_female: "महिला",
    sort_by: "क्रमवारी",
    sort_newest: "नवीनतम",
    sort_wage: "रोजंदारी (जास्त ते कमी)",
    apply_filters: "फिल्टर लागू करा",
    
    // Sections
    browse_opportunities: "संधी वर्णन करा",
    all_posts: "डॅशबोर्ड विहंगावलोकन",
    farmer_posts: "माझा जॉब इतिहास",
    worker_posts: "कामगार शोधा",
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
    post_new_job: "नवीन काम पोस्ट करा",
    
    // Messages
    logged_in_worker: "आप कामगार म्हणून लॉगिन केले आहात",
    logged_in_farmer: "तुम्ही शेतकरी म्हणून लॉग इन केले आहे",
    
    // Dashboard Titles
    worker_dashboard: "कामगार डॅशबोर्ड",
    farmer_dashboard: "शेतकरी डॅशबोर्ड",
    available_jobs: "उपलब्ध नोकऱ्या",
    all_job_posts: "सर्व नोकरी पोस्ट",
    all_available_workers: "सर्व उपलब्ध कामगार",

    // NEW DASHBOARD & FORM ADDITIONS
    need_help_farm: "शेतावर मदतीची गरज आहे का?",
    post_job_desc: "स्थानिक कामगार शोधण्यासाठी आता काम पोस्ट करा.",
    post_new_job_btn: "➕ नवीन काम पोस्ट करा",
    job_history_title: "माझा जॉब पोस्टिंग इतिहास",
    job_details: "१. कामाचा तपशील",
    job_title: "कामाचे शीर्षक",
    wage_per_day: "रोजंदारी (₹)",
    complaint_submitted_success: "तुमची तक्रार प्रशासकाकडे सुरक्षितपणे पाठवली गेली आहे.",
    report_issue: "तक्रार नोंदवा",
    my_applications: "माझे अर्ज",
    available_workers: "उपलब्ध कामगार",
    no_workers_found: "कोणतेही कामगार आढळले नाहीत",
    no_workers_desc: "सध्या तुमच्या निकषांशी जुळणारे कामगार उपलब्ध नाहीत.",
    skills_label: "कौशल्ये",
    availability_label: "उपलब्धता",
    contact_label: "संपर्क",
    im_interested_btn: "मला स्वारस्य आहे",
    other_local_jobs: "इतर स्थानिक कामे",
    wage_label: "रोजंदारी",
    needed_label: "आवश्यक",
    my_posted_jobs_history: "माझा पोस्ट केलेला जॉब इतिहास",
    no_jobs_posted: "अद्याप कोणतेही काम पोस्ट केलेले नाही",
    no_jobs_desc: "जेव्हा तुम्ही एखादे काम पोस्ट कराल, तेव्हा तुमचा इतिहास येथे दिसेल.",
    status_completed: "पूर्ण झाले",
    status_open: "खुले",
    posted_label: "पोस्ट केले",
    responses_label: "प्रतिसाद",
    mark_as_full_btn: "पूर्ण झाले म्हणून खूण करा",
    delete_post_btn: "पोस्ट हटवा",
    post_new_job_req: "नवीन कामाची आवश्यकता पोस्ट करा",
    step_1_job_details: "१. कामाचा तपशील",
    job_title_label: "कामाचे शीर्षक",
    wage_per_day_label: "रोजंदारी (₹)",
    job_desc_label: "कामाचे वर्णन",
    step_2_worker_req: "२. कामगारांच्या आवश्यकता",
    number_needed_label: "आवश्यक संख्या",
    preferred_gender_label: "प्राधान्य दिलेले लिंग",
    gender_any: "कोणतेही",
    skills_required_label: "आवश्यक कौशल्ये",
    step_3_location: "३. ठिकाण आणि वेळ",
    farm_location_label: "शेताचे ठिकाण",
    start_date_label: "सुरुवातीची तारीख आणि वेळ",
    end_date_label: "शेवटची तारीख आणि वेळ",
    post_job_submit_btn: "काम पोस्ट करा",
    select_state: "१. राज्य निवडा",
    first_select_state: "२. प्रथम राज्य निवडा",
    select_village: "३. गाव / शहर निवडा",

    report_issue_title: "तक्रार नोंदवा",
    report_issue_desc: "तुम्हाला दुसऱ्या वापरकर्त्याबद्दल काही अडचण असल्यास (उदा. पैसे न देणे, बनावट खाते, छळ), कृपया येथे नोंदवा. आमचे प्रशासक त्वरित चौकशी करतील.",
    issue_type_label: "समस्येचा प्रकार",
    select_category: "श्रेणी निवडा...",
    payment_issue: "पैसे न देणे",
    fake_profile: "बनावट खाते / फसवणूक",
    bad_behavior: "गैरवर्तन / छळ",
    other: "इतर",
    details_label: "तपशील",
    details_placeholder: "कृपया वापरकर्त्याचे नाव किंवा मोबाईल नंबर द्या आणि नेमके काय झाले ते सांगा...",
    cancel_btn: "रद्द करा",
    submit_complaint_btn: "तक्रार सबमिट करा",

    ready_to_work: "काम करण्यास तयार आहात?",
    post_avail_desc: "तुमची उपलब्धता पोस्ट करा जेणेकरून तुमच्या गावातील शेतकरी तुमच्याशी थेट संपर्क साधू शकतील.",
    post_avail_btn: "➕ उपलब्धता पोस्ट करा",
    available_farm_jobs: "उपलब्ध शेतीची कामे",
    no_jobs_found: "कोणतीही कामे आढळली नाहीत",
    no_jobs_found_desc: "सध्या तुमच्या निकषांशी जुळणारी कोणतीही खुली कामे नाहीत.",
    other_worker_posts: "इतर कामगारांच्या पोस्ट्स",
    worker_post_badge: "कामगाराची पोस्ट",
    my_job_applications: "माझे नोकरीचे अर्ज",
    no_applications_yet: "अद्याप कोणतेही अर्ज नाहीत",
    no_applications_desc: "जेव्हा तुम्ही एखाद्या कामासाठी अर्ज कराल, तेव्हा तो येथे दिसेल.",
    job_closed: "काम बंद झाले",
    active: "सक्रिय",
    applied_on: "अर्ज केल्याची तारीख",
    farmer_contact: "शेतकऱ्याचा संपर्क",
    post_availability_title: "तुमची उपलब्धता पोस्ट करा",
    availability_details_label: "उपलब्धतेचा तपशील",
    expected_wage_label: "अपेक्षित रोजंदारी (₹)",
    work_location_label: "कामाचे ठिकाण",

    skills_placeholder: "उदा. शेती, नांगरणी, कापणी",
    availability_placeholder: "उदा. सोमवार ते शुक्रवार उपलब्ध, सकाळी ८ ते संध्याकाळी ६",
    wage_placeholder: "उदा. ५००",
    example_start_date: "उदाहरण: ३ मार्च २०२६ सकाळी ९:००",
    example_end_date: "उदाहरण: ४ मार्च २०२६ संध्याकाळी ५:००"
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