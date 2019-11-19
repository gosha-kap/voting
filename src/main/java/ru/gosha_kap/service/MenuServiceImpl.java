package ru.gosha_kap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gosha_kap.model.Menu;
import ru.gosha_kap.repository.MenuRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> getAllUpdated() {
        return menuRepository.findAllByDateIsLike(LocalDate.now());
    }
}
