UPDATE users.auth SET auth_type = 'admin', user_id = 30 WHERE auth_key = 1;
UPDATE users.auth SET auth_type = 'user', user_id = 30 WHERE auth_key = 31;
UPDATE users.auth SET auth_type = 'user', user_id = 34 WHERE auth_key = 35;
UPDATE users.auth SET auth_type = 'user', user_id = 37 WHERE auth_key = 38;
UPDATE users.auth SET auth_type = 'ManComChairmen', user_id = 30 WHERE auth_key = 39;
UPDATE users.auth SET auth_type = 'ManComChairmen', user_id = 376 WHERE auth_key = 40;
UPDATE users.chore_and_week SET chore_id = 161, week = 'default' WHERE id = 1;
UPDATE users.chore_and_week SET chore_id = 162, week = 'default' WHERE id = 2;
UPDATE users.chore_and_week SET chore_id = 163, week = 'default' WHERE id = 3;
UPDATE users.chore_and_week SET chore_id = 164, week = 'default' WHERE id = 4;
UPDATE users.chore_and_week SET chore_id = 165, week = 'default' WHERE id = 5;
UPDATE users.chore_and_week SET chore_id = 166, week = 'default' WHERE id = 6;
UPDATE users.chore_chart_term_information SET term_start = '2020-07-01', term_end = '2020-07-14', active = 0, population = 12 WHERE id = 127;
UPDATE users.chore_chart_term_information SET term_start = '2020-01-14', term_end = '2020-01-15', active = 0, population = 5 WHERE id = 146;
UPDATE users.chore_chart_term_information SET term_start = '2020-02-01', term_end = '2020-02-02', active = 0, population = 16 WHERE id = 149;
UPDATE users.chore_chart_term_information SET term_start = '2020-01-08', term_end = '2020-01-09', active = 0, population = 16 WHERE id = 150;
UPDATE users.chore_chart_weeks SET week = 'default' WHERE id = 1;
UPDATE users.chore_charts SET active = 0 WHERE week = 'DEFAULT';
UPDATE users.chore_chore SET name = 'Sweep and Mop' WHERE id = 161;
UPDATE users.chore_chore SET name = 'Dishes' WHERE id = 162;
UPDATE users.chore_chore SET name = 'Upper Quad Bathroom' WHERE id = 163;
UPDATE users.chore_chore SET name = 'Spykes Bathroom' WHERE id = 164;
UPDATE users.chore_chore SET name = 'Large Dishes' WHERE id = 165;
UPDATE users.chore_chore SET name = 'Tables Chairs' WHERE id = 166;
UPDATE users.chore_day SET name = 'Monday' WHERE id = 167;
UPDATE users.chore_day SET name = 'Tuesday' WHERE id = 168;
UPDATE users.chore_day SET name = 'Wednesday' WHERE id = 169;
UPDATE users.chore_day SET name = 'Thursday' WHERE id = 170;
UPDATE users.chore_day SET name = 'Friday' WHERE id = 171;
UPDATE users.chore_day_with_user SET chore_chart_id = 1, user_id = 1, chore_id = 1, day_id = 1 WHERE id = 1;
UPDATE users.committee SET committee_name = 'maintenance', user_id = 17 WHERE committee_entry_id = 1;
UPDATE users.day_and_week SET day_id = 167, week = 'default' WHERE id = 1;
UPDATE users.day_and_week SET day_id = 168, week = 'default' WHERE id = 2;
UPDATE users.day_and_week SET day_id = 169, week = 'default' WHERE id = 3;
UPDATE users.day_and_week SET day_id = 170, week = 'default' WHERE id = 4;
UPDATE users.day_and_week SET day_id = 171, week = 'default' WHERE id = 5;
UPDATE users.hibernate_sequence SET next_val = 490;
UPDATE users.preferences SET user_id = 1, week_number = '1', chores_list = '1,2,0,0,0,0,2,7,0,0,0,0,3,8,0,0,0,0,4,9,0,0,0,0,5,10,0,0,0,0' WHERE preference_id = 1;
UPDATE users.preferences SET user_id = 3, week_number = '1', chores_list = '1,6,0,0,0,0,2,7,0,0,0,0,3,8,0,0,0,0,4,9,0,0,0,0,5,10,0,0,0,0' WHERE preference_id = 2;
UPDATE users.preferences SET user_id = 17, week_number = '1', chores_list = '1,6,0,0,0,0,2,7,0,4,0,0,3,8,0,0,0,0,4,9,0,0,0,0,5,10,0,0,0,0' WHERE preference_id = 3;
UPDATE users.preferences SET user_id = 18, week_number = '1', chores_list = '1,6,0,0,0,0,2,7,0,0,0,0,3,8,0,0,0,0,4,9,0,0,0,0,5,10,0,0,0,0' WHERE preference_id = 4;
UPDATE users.preferences SET user_id = 5, week_number = '2019-W47', chores_list = '1,6,0,0,0,0,2,7,0,0,0,0,3,8,0,0,0,0,4,9,0,0,0,0,5,10,0,0,0,0' WHERE preference_id = 20;
UPDATE users.user_and_id SET user_name = 'test', first_name = 'test', last_name = 'test', kappa_sigma = 1412, brother = 1, password = '$2a$10$pg3sl6NXAscT7YFxjfl6YeTUJYAZmFow13jKX2ROJ5T3qsWtK/TUy', big = 1401, active = 1 WHERE user_id = 30;
UPDATE users.user_and_id SET user_name = 'bill', first_name = 'Bill', last_name = 'Fred', kappa_sigma = 0, brother = 0, password = '$2a$10$HPL00RMeTSsJYt4PZfhHu.7HH72XdoboZwHVQjm708a.GRnOIsaP.', big = 1450, active = 1 WHERE user_id = 34;
UPDATE users.user_and_id SET user_name = 'user', first_name = 'bill', last_name = 'test', kappa_sigma = 1230, brother = 1, password = '$2a$10$n3etVgRk1wb.mRO8y2C/v.B.c1AFrFAgSEQWn9/0cZWy4JJp2QBHe', big = 1110, active = 1 WHERE user_id = 37;
UPDATE users.user_and_id SET user_name = 'RooHoo', first_name = 'Jack', last_name = 'Morse', kappa_sigma = 1411, brother = 1, password = '$2a$10$eIYvMAK0IDfuanVlRGJw4OF9Fr1AENoS3x3jxB2TPCfFIGcrGN9ga', big = 1401, active = 1 WHERE user_id = 178;
UPDATE users.user_and_id SET user_name = 'asdf', first_name = 'asdf', last_name = 'asdf', kappa_sigma = 112, brother = 1, password = '$2a$10$Vqt5ZnHr8WtJWxjgLpLR9uqvhfUEeHESQQ1RJ.4brMj5I9S2vG3Sq', big = 111, active = 1 WHERE user_id = 179;
UPDATE users.user_and_id SET user_name = 'qwer', first_name = 'qwer', last_name = 'qwer', kappa_sigma = 118, brother = 1, password = '$2a$10$VGvOfJwegAh8Xmn3gxI0ce3t1AdxfQNXFcUjS0oniAp3.wwlHRzWG', big = 117, active = 1 WHERE user_id = 180;
UPDATE users.user_and_id SET user_name = 'asdffff', first_name = 'yyy', last_name = 'yyy', kappa_sigma = 1234, brother = 1, password = '$2a$10$n0sTzksdYQaYqQiJbG9QZ.X1eCJDxAC6W86fw20RAAjXxZE6cx2gi', big = 1236, active = 1 WHERE user_id = 181;
UPDATE users.user_and_id SET user_name = 'brother1', first_name = 'asdf', last_name = 'asdf', kappa_sigma = 1000, brother = 1, password = '$2a$10$6BUbmsL4SbcU9FSomDr9xO7vglWfp5ZrLJYRqogNRFso.hBjd6ENy', big = 5, active = 1 WHERE user_id = 198;
UPDATE users.user_and_id SET user_name = 'brother2', first_name = 'second', last_name = 'brother', kappa_sigma = 999, brother = 1, password = '$2a$10$.w09dobRFgT.BGcWvVIb6eM1Z5J6.qwpGJ4sYExfs73FhskwpN8Fa', big = 4, active = 1 WHERE user_id = 199;
UPDATE users.user_and_id SET user_name = 'amSmall', first_name = 'smasl', last_name = 'amm', kappa_sigma = 99999, brother = 0, password = '$2a$10$AQ9wBsXtLZ2sPmt2.pIOLOEmNQw5GiTezoVgO6qXxsNBpjAi41eEa', big = 1000, active = 1 WHERE user_id = 200;
UPDATE users.user_and_id SET user_name = 'amBig', first_name = 'am', last_name = 'big', kappa_sigma = 99999, brother = 0, password = '$2a$10$r5HH1dQWvq7Im6NaYIrcduCAFwOPYEqEs9s7dkjncyqyz8rkzqwAi', big = 999, active = 1 WHERE user_id = 201;
UPDATE users.user_and_id SET user_name = 'FROSTY', first_name = 'Ian', last_name = 'Knight', kappa_sigma = 1412, brother = 1, password = '$2a$10$w7RjdxD.N81Xs0afCP1bqu7Os76cRiMaF/foGIpNAlwMj1/qowRaq', big = 1402, active = 1 WHERE user_id = 376;
UPDATE users.user_and_id SET user_name = 'Marcus', first_name = 'Marcus', last_name = 'Moss', kappa_sigma = 1399, brother = 1, password = '$2a$10$9tosbe6clCRm43kKdm4uS.tKP72CekP/AkwwJ3FWh7op1oKsfuJle', big = 1381, active = 1 WHERE user_id = 467;
UPDATE users.user_and_id SET user_name = 'qwe', first_name = 'qwe', last_name = 'qwe', kappa_sigma = 2, brother = 1, password = '$2a$10$Zn8kc26tJj5QVzVqMeu6u.T3r5.Iq3FQXMwRm.67kIMQGVcS5je7e', big = 1, active = 1 WHERE user_id = 484;
UPDATE users.user_and_id SET user_name = 'gggg', first_name = 'gg', last_name = 'gg', kappa_sigma = 1, brother = 1, password = '$2a$10$DQc7nYR5e9okrT9vMsuoW.euT3t1LgOhGpd9Su9feoP0.ie43eKDC', big = 3, active = 1 WHERE user_id = 487;
UPDATE users.user_and_id SET user_name = 'ggggggg', first_name = 'h', last_name = 'h', kappa_sigma = 2, brother = 1, password = '$2a$10$sc.IRytXitNu6p2vBN2JKeiZu3RbGefD98S2hhMgD5HgFWo3jBj.i', big = 1, active = 1 WHERE user_id = 488;
UPDATE users.user_and_id SET user_name = 'gggggggg', first_name = 'ggggg', last_name = 'gggggg', kappa_sigma = 555, brother = 1, password = '$2a$10$akGHo7hsqHcGr4dA1eo0BOJYI9VlHYJejiqheoVY8a.kbSlXhcnhO', big = 555, active = 1 WHERE user_id = 489;
UPDATE users.user_preference SET week = 'default', day_id = 167, chore_id = 161, preference_ranking = 1, user_id = 30 WHERE preference_id = 468;
UPDATE users.user_preference SET week = 'default', day_id = 168, chore_id = 161, preference_ranking = 2, user_id = 30 WHERE preference_id = 469;
UPDATE users.user_preference SET week = 'default', day_id = 171, chore_id = 164, preference_ranking = 6, user_id = 467 WHERE preference_id = 470;
UPDATE users.user_preference SET week = 'default', day_id = 171, chore_id = 162, preference_ranking = 1, user_id = 467 WHERE preference_id = 471;
UPDATE users.user_preference SET week = 'default', day_id = 170, chore_id = 164, preference_ranking = 2, user_id = 467 WHERE preference_id = 472;
UPDATE users.user_preference SET week = 'default', day_id = 169, chore_id = 164, preference_ranking = 3, user_id = 467 WHERE preference_id = 473;
UPDATE users.user_preference SET week = 'default', day_id = 168, chore_id = 164, preference_ranking = 4, user_id = 467 WHERE preference_id = 474;
UPDATE users.user_preference SET week = 'default', day_id = 167, chore_id = 164, preference_ranking = 5, user_id = 467 WHERE preference_id = 475;
UPDATE users.user_preference SET week = 'default', day_id = 170, chore_id = 163, preference_ranking = 7, user_id = 467 WHERE preference_id = 476;
UPDATE users.user_preference SET week = 'default', day_id = 169, chore_id = 163, preference_ranking = 8, user_id = 467 WHERE preference_id = 477;
UPDATE users.user_preference SET week = 'default', day_id = 168, chore_id = 163, preference_ranking = 9, user_id = 467 WHERE preference_id = 478;
UPDATE users.user_preference SET week = 'default', day_id = 167, chore_id = 163, preference_ranking = 10, user_id = 467 WHERE preference_id = 479;
UPDATE users.user_preference SET week = 'default', day_id = 170, chore_id = 161, preference_ranking = 0, user_id = 30 WHERE preference_id = 480;
UPDATE users.user_preference SET week = 'default', day_id = 171, chore_id = 161, preference_ranking = 0, user_id = 30 WHERE preference_id = 481;
UPDATE users.user_preference SET week = 'default', day_id = 169, chore_id = 163, preference_ranking = 0, user_id = 30 WHERE preference_id = 482;
UPDATE users.user_preference SET week = 'default', day_id = 170, chore_id = 163, preference_ranking = 0, user_id = 30 WHERE preference_id = 483;