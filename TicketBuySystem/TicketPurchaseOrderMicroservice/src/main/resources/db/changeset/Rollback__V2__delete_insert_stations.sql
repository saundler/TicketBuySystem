-- V2__insert_stations_rollback.sql

-- Удаление станций из таблицы stations
DELETE FROM stations WHERE station IN
                           ('Station 1',
                            'Station 2',
                            'Station 3',
                            'Station 4',
                            'Station 5',
                            'Station 6',
                            'Station 7',
                            'Station 8',
                            'Station 9',
                            'Station 10');
