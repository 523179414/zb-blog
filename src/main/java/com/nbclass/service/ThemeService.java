package com.nbclass.service;

import com.nbclass.framework.theme.ZbTheme;

public interface ThemeService {

    void useTheme(ZbTheme theme);

    ZbTheme selectCurrent();

    void initThymeleafVars();

    void updateSettings(String settingsJson);
}
