namespace MES_Client
{
    partial class CreateLot
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.labelLotId = new System.Windows.Forms.Label();
            this.textBoxProdQty = new System.Windows.Forms.TextBox();
            this.labelOper = new System.Windows.Forms.Label();
            this.labelFlow = new System.Windows.Forms.Label();
            this.labelProd = new System.Windows.Forms.Label();
            this.labelProdQty = new System.Windows.Forms.Label();
            this.comboBoxProd = new System.Windows.Forms.ComboBox();
            this.comboBoxFlow = new System.Windows.Forms.ComboBox();
            this.comboBoxOper = new System.Windows.Forms.ComboBox();
            this.textBoxLotId = new System.Windows.Forms.TextBox();
            this.btnCreate = new System.Windows.Forms.Button();
            this.btnReset = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // labelLotId
            // 
            this.labelLotId.AutoSize = true;
            this.labelLotId.Font = new System.Drawing.Font("맑은 고딕", 13.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.labelLotId.Location = new System.Drawing.Point(12, 45);
            this.labelLotId.Name = "labelLotId";
            this.labelLotId.Size = new System.Drawing.Size(84, 32);
            this.labelLotId.TabIndex = 0;
            this.labelLotId.Text = "Lot ID";
            // 
            // textBoxProdQty
            // 
            this.textBoxProdQty.Location = new System.Drawing.Point(136, 275);
            this.textBoxProdQty.Name = "textBoxProdQty";
            this.textBoxProdQty.Size = new System.Drawing.Size(149, 25);
            this.textBoxProdQty.TabIndex = 1;
            // 
            // labelOper
            // 
            this.labelOper.AutoSize = true;
            this.labelOper.Font = new System.Drawing.Font("맑은 고딕", 13.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.labelOper.Location = new System.Drawing.Point(12, 100);
            this.labelOper.Name = "labelOper";
            this.labelOper.Size = new System.Drawing.Size(71, 32);
            this.labelOper.TabIndex = 2;
            this.labelOper.Text = "Oper";
            // 
            // labelFlow
            // 
            this.labelFlow.AutoSize = true;
            this.labelFlow.Font = new System.Drawing.Font("맑은 고딕", 13.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.labelFlow.Location = new System.Drawing.Point(12, 155);
            this.labelFlow.Name = "labelFlow";
            this.labelFlow.Size = new System.Drawing.Size(69, 32);
            this.labelFlow.TabIndex = 3;
            this.labelFlow.Text = "Flow";
            // 
            // labelProd
            // 
            this.labelProd.AutoSize = true;
            this.labelProd.Font = new System.Drawing.Font("맑은 고딕", 13.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.labelProd.Location = new System.Drawing.Point(12, 210);
            this.labelProd.Name = "labelProd";
            this.labelProd.Size = new System.Drawing.Size(69, 32);
            this.labelProd.TabIndex = 4;
            this.labelProd.Text = "Prod";
            // 
            // labelProdQty
            // 
            this.labelProdQty.AutoSize = true;
            this.labelProdQty.Font = new System.Drawing.Font("맑은 고딕", 13.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.labelProdQty.Location = new System.Drawing.Point(12, 265);
            this.labelProdQty.Name = "labelProdQty";
            this.labelProdQty.Size = new System.Drawing.Size(118, 32);
            this.labelProdQty.TabIndex = 5;
            this.labelProdQty.Text = "Prod Qty";
            // 
            // comboBoxProd
            // 
            this.comboBoxProd.FormattingEnabled = true;
            this.comboBoxProd.Location = new System.Drawing.Point(136, 220);
            this.comboBoxProd.Name = "comboBoxProd";
            this.comboBoxProd.Size = new System.Drawing.Size(149, 23);
            this.comboBoxProd.TabIndex = 6;
            // 
            // comboBoxFlow
            // 
            this.comboBoxFlow.FormattingEnabled = true;
            this.comboBoxFlow.Location = new System.Drawing.Point(136, 165);
            this.comboBoxFlow.Name = "comboBoxFlow";
            this.comboBoxFlow.Size = new System.Drawing.Size(149, 23);
            this.comboBoxFlow.TabIndex = 7;
            // 
            // comboBoxOper
            // 
            this.comboBoxOper.FormattingEnabled = true;
            this.comboBoxOper.Location = new System.Drawing.Point(136, 110);
            this.comboBoxOper.Name = "comboBoxOper";
            this.comboBoxOper.Size = new System.Drawing.Size(149, 23);
            this.comboBoxOper.TabIndex = 8;
            this.comboBoxOper.DropDown += new System.EventHandler(this.comboBoxOper_DropDown);
            // 
            // textBoxLotId
            // 
            this.textBoxLotId.ImeMode = System.Windows.Forms.ImeMode.Disable;
            this.textBoxLotId.Location = new System.Drawing.Point(136, 52);
            this.textBoxLotId.Name = "textBoxLotId";
            this.textBoxLotId.Size = new System.Drawing.Size(149, 25);
            this.textBoxLotId.TabIndex = 9;
            this.textBoxLotId.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.textBoxLotId_KeyPress);
            // 
            // btnCreate
            // 
            this.btnCreate.Font = new System.Drawing.Font("맑은 고딕", 13.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.btnCreate.Location = new System.Drawing.Point(293, 450);
            this.btnCreate.Name = "btnCreate";
            this.btnCreate.Size = new System.Drawing.Size(140, 49);
            this.btnCreate.TabIndex = 10;
            this.btnCreate.Text = "생성";
            this.btnCreate.UseVisualStyleBackColor = true;
            this.btnCreate.Click += new System.EventHandler(this.btnCreate_Click);
            // 
            // btnReset
            // 
            this.btnReset.Font = new System.Drawing.Font("맑은 고딕", 13.8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.btnReset.Location = new System.Drawing.Point(147, 450);
            this.btnReset.Name = "btnReset";
            this.btnReset.Size = new System.Drawing.Size(140, 49);
            this.btnReset.TabIndex = 11;
            this.btnReset.Text = "초기화";
            this.btnReset.UseVisualStyleBackColor = true;
            this.btnReset.Click += new System.EventHandler(this.btnReset_Click);
            // 
            // CreateLot
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(445, 511);
            this.Controls.Add(this.btnReset);
            this.Controls.Add(this.btnCreate);
            this.Controls.Add(this.textBoxLotId);
            this.Controls.Add(this.comboBoxOper);
            this.Controls.Add(this.comboBoxFlow);
            this.Controls.Add(this.comboBoxProd);
            this.Controls.Add(this.labelProdQty);
            this.Controls.Add(this.labelProd);
            this.Controls.Add(this.labelFlow);
            this.Controls.Add(this.labelOper);
            this.Controls.Add(this.textBoxProdQty);
            this.Controls.Add(this.labelLotId);
            this.Name = "CreateLot";
            this.Text = "CreateLot";
            this.Load += new System.EventHandler(this.CreateLot_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label labelLotId;
        private System.Windows.Forms.TextBox textBoxProdQty;
        private System.Windows.Forms.Label labelOper;
        private System.Windows.Forms.Label labelFlow;
        private System.Windows.Forms.Label labelProd;
        private System.Windows.Forms.Label labelProdQty;
        private System.Windows.Forms.ComboBox comboBoxProd;
        private System.Windows.Forms.ComboBox comboBoxFlow;
        private System.Windows.Forms.ComboBox comboBoxOper;
        private System.Windows.Forms.TextBox textBoxLotId;
        private System.Windows.Forms.Button btnCreate;
        private System.Windows.Forms.Button btnReset;
    }
}