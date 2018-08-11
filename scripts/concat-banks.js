const fs = require('fs');
const path = require('path');

const banksDir = path.join(__dirname, '../source/banks');

const distDir = path.join(__dirname, '../dist');
if (!fs.existsSync(distDir)) fs.mkdirSync(distDir);

const nations = fs.readdirSync(banksDir);

for (nation of nations) {
    const nationDir = path.join(banksDir, nation);
    const banks = fs.readdirSync(nationDir);

    const bankList = banks.map(e => path.join(nationDir, e))
        .map(e => fs.readFileSync(e, { encoding: 'utf8' }))
        .map(e => JSON.parse(e));

    const json = JSON.stringify(bankList, null, 2);
    fs.writeFileSync(path.join(distDir, `banks-${nation}.json`), json);
}
